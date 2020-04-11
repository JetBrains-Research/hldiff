import { Component, OnInit } from '@angular/core';
import { HLDiffService } from '../../hldiff.service';
import { DiffData } from '../../diff-data';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.scss']
})
export class UploadComponent implements OnInit {

  diffSource: string;
  diffData: string;

  constructor(private diffService: HLDiffService) {
  }

  ngOnInit(): void {
  }

  uploadDiff() {
    const diffData = { source: this.diffSource, data: this.diffData } as DiffData;
    this.diffService.uploadDiff(diffData).subscribe();
  }
}
