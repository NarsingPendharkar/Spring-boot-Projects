package org.chatapp.all;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/api/chat")
public class ChatController {

    private final GroqChatService service;

    public ChatController(GroqChatService service) {
        this.service = service;
    }

    @PostMapping("/ask")
    public Mono<AskResponse> ask(@RequestBody AskRequest req) {
        return service.ask(req.getQuestion())
                      .map(json -> {
                          String answer = json.path("choices").get(0)
                                              .path("message")
                                              .path("content")
                                              .asText();
                          return new AskResponse(answer);
                      });
    }
    
    @GetMapping("/")
    public String home() {
        return "chat"; // loads templates/chat.html
    }

    @PostMapping("/ask-ui")
    public Mono<String> askUI(@RequestParam("question") String question, Model model) {
        // Validation: empty question
        if (question == null || question.trim().isEmpty()) {
            model.addAttribute("error", "Please enter a question before submitting.");
            return Mono.just("chat");
        }

        return service.ask(question)
                .map(json -> {
                    if (json.has("error")) {  
                        return "ERROR: " + json.path("error").path("message").asText("Something went wrong!");
                    }
                    return json.path("choices").get(0)
                               .path("message")
                               .path("content")
                               .asText("No answer received.");
                })
                .map(answer -> {
                    if (answer.startsWith("ERROR:")) {
                        model.addAttribute("error", answer.substring(6));
                    } else {
                        model.addAttribute("question", question);
                        model.addAttribute("answer", answer);
                    }
                    return "chat";  // reload same page with result or error
                })
                .onErrorResume(ex -> {
                    model.addAttribute("error", "Failed to get response: " + ex.getMessage());
                    return Mono.just("chat");
                });
    }

    
}
