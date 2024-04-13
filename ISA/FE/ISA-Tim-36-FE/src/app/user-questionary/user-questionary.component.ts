import { Component, OnInit } from '@angular/core';
import { Questionnaire } from '../questionnaire'


@Component({
  selector: 'app-user-questionary',
  templateUrl: './user-questionary.component.html',
  styleUrls: ['./user-questionary.component.css']
})
export class UserQuestionaryComponent implements OnInit {

  questinnare?: Questionnaire[];

  constructor() { }

  ngOnInit(): void {
    this.questinnare = [{

        id:1,
        "question":"Da li imate manje od 50kg?",
        "answer":"",
        "userId":1,
        "questinnaryId":101
    },
    {
      id:2,
      "question":"Da li ste imali simptome prehlade, neke bolesti ili se jednostavno ne osećate dobro?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:3,
      "question":"Da li se trenutno osećate zdravim, sposobnim i odmornim da date krv ili komponente krvi?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:4,
      "question":"Da li ste nešto jeli pre dolaska na davanje krvi ili komponente krvi?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:5,
      "question":"Da li se bavite opasnim zanimanjem ili hobijem?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:6,
      "question":"Da li redovno (svakodnevno) uzimate bilo kakve lekove?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:7,
      "question":"Da li ste poslednja 2-3 dana uzimali bilo kakve lekove (npr. Brufen, Kafetin, Analgin...)?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:8,
      "question":"Da li stalno uzimate Aspirin (Cardiopirin)? Da li ste ga uzimali u poslednjih 5 dana?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:9,
      "question":"Da li ste do sada ispitivani ili lečeni u bolnici ili ste trenutno na ispitivanju ili bolovanju?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    },
    {
      id:10,
      "question":"Da li ste vadili zub u proteklih 7 dana?",
      "answer":"",
      "userId":1,
      "questinnaryId":101
    }]
  }

}
