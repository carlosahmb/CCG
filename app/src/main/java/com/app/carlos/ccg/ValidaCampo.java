package com.app.carlos.ccg;

import android.widget.EditText;

public class ValidaCampo {
    public static int checacampoid(EditText editTextId) {
        return (editTextId.getText().toString().trim().length());
    }

    public static int checacamponome(EditText editTextNome) {
        return (editTextNome.getText().toString().trim().length());
    }

    public static int checacampoemail(EditText editTextEmail) {
        return (editTextEmail.getText().toString().trim().length());
    }

}
