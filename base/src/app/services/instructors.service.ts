import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Instructor } from '../model/instructor.model';

@Injectable({
  providedIn: 'root'
})
export class InstructorsService {

  constructor(private http:HttpClient) { }

  public findAllInstructors(): Observable<Array<Instructor>> {
    return this.http.get<Array<Instructor>>(environment.backendHost + "/instructors/all");
  }
}
