import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatGridListModule} from '@angular/material/grid-list'
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field'
import {MatButtonModule} from '@angular/material/button';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatSnackBarModule} from '@angular/material/snack-bar'
const materialComponents = [
  MatGridListModule,
  MatToolbarModule,
  MatCardModule,
  MatFormFieldModule,
  MatButtonModule,
  MatTooltipModule,
  MatSnackBarModule,
]


@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    materialComponents
  ],
  exports : [
    materialComponents
  ]
})
export class MaterialModule { }
