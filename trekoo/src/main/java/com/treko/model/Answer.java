package com.treko.model;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.treko.audit.AuditModel;

@Entity
@Table(name = "answers")
public class Answer extends AuditModel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "answer_generator")
    @SequenceGenerator(
            name = "answer_generator",
            sequenceName = "answer_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(columnDefinition = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Question question;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name="answerMap",
	joinColumns={@JoinColumn(name="answerId")},
	inverseJoinColumns={@JoinColumn(name="subAnswerId")})
    private List<Answer> subAnswers = new ArrayList<Answer>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.MERGE})
    @JoinTable(name="answerMap",
	joinColumns={@JoinColumn(name="subAnswerId")},
	inverseJoinColumns={@JoinColumn(name="answerId")})
    @JsonIgnore
    private List<Answer> answer = new ArrayList<Answer>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<Answer> getAnswer() {
		return answer;
	}

	public void setAnswer(List<Answer> answer) {
		this.answer = answer;
	}

	public List<Answer> getSubAnswers() {
		return subAnswers;
	}

	public void setSubAnswers(List<Answer> subAnswers) {
		this.subAnswers = subAnswers;
	}

}