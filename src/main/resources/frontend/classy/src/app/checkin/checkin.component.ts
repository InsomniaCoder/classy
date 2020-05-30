import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {environment} from '../../environments/environment';
import {HttpClient} from "@angular/common/http";
import {catchError} from "rxjs/operators";

@Component({
  selector: 'app-checkin',
  templateUrl: './checkin.component.html',
  styleUrls: ['./checkin.component.css']
})
export class CheckinComponent implements OnInit {

  className: String;
  classId: Number;

  telephoneNumber: String;

  constructor(private route: ActivatedRoute, private http: HttpClient) {
  }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.className = params['className'];
      this.classId = params['classId'];
    });
  }

  checkIn() {
    // alert(this.telephoneNumber);
    let checkInAPIUrl = environment.serverUrl + "/classes/" + this.classId + "/attendances?telephoneNumber=" + this.telephoneNumber;
    console.log("calling api : "+checkInAPIUrl);
    let objectObservable = this.http.post(checkInAPIUrl, {});
    objectObservable.subscribe(result => {
      alert("checked in successfully!");
    })
  }

  onKey(value: string) {
    this.telephoneNumber = value;
  }
}
