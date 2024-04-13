package com.example.ISA.tim6.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "questionnaire")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "question")
    private  String question;
    
    @Column(name = "answer")
    private  boolean answer;
    
    @Column(name = "userId")
    private  long userId;
    
    @Column(name = "questinnaryId")
    private  long questinnaryId;
    
    public Questionnaire() {
    	
    }

	public Questionnaire(long id, String question, boolean answer, long userId, long questinnaryId) {
		super();
		this.question = question;
		this.answer = answer;
		this.userId = userId;
		this.questinnaryId = questinnaryId;
	}

}
