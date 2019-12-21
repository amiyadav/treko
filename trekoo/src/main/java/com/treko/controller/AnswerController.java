package com.treko.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.treko.exception.ResourceNotFoundException;
import com.treko.model.Answer;
import com.treko.repository.AnswerRepository;
import com.treko.repository.QuestionRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {

	@Autowired
	private AnswerRepository answerRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@GetMapping("/questions/{questionId}/answers")
	public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId) {
		return answerRepository.findByQuestionId(questionId);
	}
	
	@GetMapping("/questions/answers/{answerId}")
	public Answer getAnswersByAnswerId(@PathVariable Long answerId) {
		return answerRepository.findById(answerId).get();
	}

	@PostMapping("/questions/{questionId}/answers")
	public Answer addAnswer(@PathVariable Long questionId, @Valid @RequestBody Answer answer) {
		return questionRepository.findById(questionId).map(question -> {
			answer.setQuestion(question);
			return answerRepository.save(answer);
		}).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + questionId));
	}

	@PostMapping("/questions/{questionId}/subanswers/{answerId}")
	public Answer addSubAnswer(@PathVariable(required = true) Long questionId,
			@PathVariable(required = true) Long answerId, @Valid @RequestBody Answer answer) {

		if (!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id " + questionId);
		}

		if (!answerRepository.existsById(answerId)) {
			throw new ResourceNotFoundException("Answer not found with id " + answerId);
		}

		return answerRepository.findById(answerId).map(subAns -> {
			answer.setQuestion(questionRepository.findById(questionId).get());
			subAns.getSubAnswers().add(answer);
			return answerRepository.save(subAns);
		}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

	}

	@PutMapping("/questions/{questionId}/answers/{answerId}")
	public Answer updateAnswer(@PathVariable Long questionId, @PathVariable Long answerId,
			@Valid @RequestBody Answer answerRequest) {
		if (!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id " + questionId);
		}
		return answerRepository.findById(answerId).map(answer -> {
			answer.setText(answerRequest.getText());
			return answerRepository.save(answer);
		}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));
	}

	@DeleteMapping("/questions/{questionId}/answers/{answerId}")
	public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId, @PathVariable Long answerId) {
		if (!questionRepository.existsById(questionId)) {
			throw new ResourceNotFoundException("Question not found with id " + questionId);
		}

		return answerRepository.findById(answerId).map(answer -> {
			answerRepository.delete(answer);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("Answer not found with id " + answerId));

	}
}