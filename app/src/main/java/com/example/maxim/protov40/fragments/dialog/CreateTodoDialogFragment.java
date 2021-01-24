package com.example.maxim.protov40.fragments.dialog;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.maxim.protov40.R;
import com.example.maxim.protov40.util.Session;

import java.util.Calendar;

public class CreateTodoDialogFragment extends DialogFragment{
    public interface ICreateTodo{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    private ICreateTodo mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
//        View view = inflater.inflate(R.layout.create_todo_dialog_layout, null);
        builder.setTitle(R.string.create_todo_title);
//        TextView deadline = (TextView) view.findViewById(R.id.todo_data_edit);
//        deadline.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Calendar calendar = Calendar.getInstance();
//                int calendarYear = calendar.get(Calendar.YEAR);
//                int calendarMonth = calendar.get(Calendar.MONTH);
//                int calendarDay = calendar.get(Calendar.DAY_OF_MONTH);
//                DatePickerDialog date = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        deadline.setText(dayOfMonth + "/" + month + "/" + year);
//                    }
//                }, calendarYear, calendarMonth, calendarDay);
//                date.setTitle("Deadline set");
//                date.show();
//            }
//        });

        builder.setView(inflater.inflate(R.layout.create_todo_dialog_layout, null))
                .setPositiveButton(R.string.create_btn, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogPositiveClick(CreateTodoDialogFragment.this);
                    }
                })
                .setNegativeButton(R.string.cancel_btn, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mListener.onDialogNegativeClick(CreateTodoDialogFragment.this);
                    }
                });
        return builder.create();
    }

    public void setmListener(ICreateTodo mListener) {
        this.mListener = mListener;
    }
}
