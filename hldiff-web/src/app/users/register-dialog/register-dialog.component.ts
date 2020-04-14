import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';
import { MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-register-dialog',
  templateUrl: './register-dialog.component.html',
  styleUrls: ['./register-dialog.component.scss']
})
export class RegisterDialogComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService,
              public dialogRef: MatDialogRef<RegisterDialogComponent>) {
  }

  form: FormGroup = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.min(4), Validators.pattern('[A-Za-z0-9_]+')]),
    password: new FormControl('', [Validators.required, Validators.min(6)]),
  });

  error: string;

  ngOnInit(): void {
  }

  submit() {
    this.error = null;
    const result = this.authenticationService.register(this.form.controls.username.value, this.form.controls.password.value);
    result.subscribe(
      _ => this.dialogRef.close(),
      _ => this.error = 'Failed to register user with given username'
    );
  }
}
