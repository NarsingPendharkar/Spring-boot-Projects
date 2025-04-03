package org.school.service;

import java.util.List;

import org.school.entity.Fee;
import org.school.repository.FeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeeService {
	
	@Autowired
	private FeeRepository feeRepository;
	
	//get fee by id
	public Fee getFeeById(Long id) {
        return feeRepository.findById(id).orElse(null);
    }
	// get fee by studentid
	 public Fee getFeeByStudentId(Long studentId) {
        return feeRepository.findByStudentId(studentId).orElse(null);
    }
    // find all fee
	 public List<Fee> findAllFees() {
        return feeRepository.findAll();
    }
    // save fee
	 public void saveFee(Fee fee) {
        feeRepository.save(fee);
    }
    // delete fee by id
     public void deleteFeeById(Long id) {
        feeRepository.deleteById(id);
    }
    // update fee by id
     public void updateFee(Fee fee) {
        feeRepository.save(fee);
    }
	/*
	 * // get fee by student id and status public Fee
	 * getFeeByStudentIdAndStatus(Long studentId, String status) { return
	 * feeRepository.findByStudentIdAndStatus(studentId, status).orElse(null); }
	 */

}
