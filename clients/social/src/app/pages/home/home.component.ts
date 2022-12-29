import {Component, OnInit} from '@angular/core';
import {AuditService} from "../../services/audit.service";
import {Pageable} from "../../shared/domain/Pageable";
import Audit from "../../shared/domain/Audit";
import {Observable} from "rxjs";
import {PageEvent} from "@angular/material/paginator";
import {CdkTableDataSourceInput} from "@angular/cdk/table";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  audits!: Observable<Pageable.Page<Audit>>;
  displayedColumns = ["id",
    "publisher",
    "title"];

  constructor(private readonly auditService: AuditService) {
  }

  ngOnInit(): void {
    this.audits = this.auditService.get({page: 0, size: 100, sort: ["title,desc"]})
  }

  getServerData($event: PageEvent) {
    console.log($event)
  }

  getValue(dataSource: CdkTableDataSourceInput<unknown>) {
    const data = dataSource as Array<Audit>
    return data.map(value => value.selected)
  }
}
