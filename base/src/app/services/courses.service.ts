import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CoursesService {

  constructor(private http:HttpClient) {

  }

  public searchCourses(keyword:string, currentPage:number, pageSize:number) : any {
    return this.http.get(environment.backendHost + "/courses?keyword=" + 
                          keyword + "&page" + currentPage + "&size" + pageSize);
  }
}
