import { Component, OnInit } from '@angular/core';
import { DiffData } from '../../diff-data';
import { HLDiffService } from '../../hldiff.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html',
  styleUrls: ['./file-upload.component.scss']
})
export class FileUploadComponent implements OnInit {

  isLoading = false;
  errorMessage: string;

  total = 0;
  uploaded = 0;

  constructor(private diffService: HLDiffService,
              public dialogRef: MatDialogRef<FileUploadComponent>) {
  }

  ngOnInit(): void {
  }

  fileChanged(event: Event) {
    console.log(event.target);
  }

  onFileSelected() {
    const inputNode: any = document.querySelector('#file');
    this.errorMessage = '';

    if (typeof (FileReader) !== 'undefined') {
      const files = inputNode.files;
      this.total = files.length;

      for (const file of files) {
        const reader = new FileReader();

        reader.onload = (e: any) => {
          const diff = e.target.result;
          const diffName = file.name.slice(0, -5);
          console.log(diffName);
          if (JSON.parse(e.target.result)) {
            this.uploadDiff(diff, diffName);
          } else {
            this.errorMessage = `File ${file.name} is not a valid JSON.`;
            console.log(`File ${file.name} is not a valid JSON.`);
            return;
          }
        };

        reader.readAsText(file);
      }
    }
  }

  private uploadDiff(diff: string, name: string) {
    this.errorMessage = '';
    this.isLoading = true;
    const diffData = { source: name, data: diff, reviews: [] } as DiffData;
    const uploadedDiff$ = this.diffService.uploadDiff(diffData);

    uploadedDiff$.subscribe(
      _ => {
        this.uploaded += 1;
        if (this.uploaded >= this.total) {
          this.isLoading = false;
          this.dialogRef.close();
        }
      },
      err => {
        this.isLoading = false;
        this.errorMessage = err.message;
      },
    );
  }
}
