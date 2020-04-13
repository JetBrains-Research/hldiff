import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationService } from '../authentication.service';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-login-dialog',
  templateUrl: './login-dialog.component.html',
  styleUrls: ['./login-dialog.component.scss']
})
export class LoginDialogComponent implements OnInit {

  form: FormGroup = new FormGroup({
    username: new FormControl(''),
    password: new FormControl(''),
  });

  error: string;

  constructor(private authenticationService: AuthenticationService,
              public dialogRef: MatDialogRef<LoginDialogComponent>) {
  }

  ngOnInit(): void {
  }

  submit() {
    this.error = null;
    const result = this.authenticationService.authenticate(this.form.controls.username.value, this.form.controls.password.value);
    if (result) {
      this.dialogRef.close();
    } else {
      this.error = 'Wrong username or password';
    }
  }
}
