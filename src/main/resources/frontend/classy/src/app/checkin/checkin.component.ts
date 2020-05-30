import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-checkin',
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.css']
})
export class CheckinComponent implements OnInit {

  className: String;

  constructor() {
    this.className = "Math"
  }

  ngOnInit(): void {
  }

}
