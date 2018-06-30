package com.example.bahung.vtasknet.controller.dialogfragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bahung.vtasknet.R;

/**
 * Created by bahung on 18/06/2018.
 **/

public class CreateProjectDialog extends DialogFragment{
    EditText edtName;
    CreateProject createProject;
    public CreateProjectDialog() {
        // Empty constructor required for DialogFragment
    }

    public static CreateProjectDialog newInstance(String title) {
        CreateProjectDialog frag = new CreateProjectDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        String title = getArguments().getString("title");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_create_project_dialog, null);

        edtName = view.findViewById(R.id.edtName);

        alertDialogBuilder.setView(view).setTitle(title).setPositiveButton("Tao moi",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // on success
                Toast.makeText(getContext(), "Tao moi", Toast.LENGTH_SHORT).show();
                String txtName = edtName.getText().toString();
                Toast.makeText(getActivity(), txtName, Toast.LENGTH_SHORT).show();
                createProject.sendTitleProjectForData(txtName);

            }
        })
                .setNegativeButton("Huy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (dialog != null) {
                            dialog.dismiss();
                        }
                    }

                });


        return alertDialogBuilder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            createProject = (CreateProject) context;
        }catch(ClassCastException e){
            throw new ClassCastException(context.toString()+" must implements sendTitleProjectForData");
        }
    }

    public interface CreateProject {
        void sendTitleProjectForData(String title);
    }
}
