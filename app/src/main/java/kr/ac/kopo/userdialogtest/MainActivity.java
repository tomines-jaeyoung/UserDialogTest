package kr.ac.kopo.userdialogtest;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textName, textEmail;

    Button btn;
    EditText editName, editEmail;
    View dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textName = findViewById(R.id.text_name);
        textEmail = findViewById(R.id.text_email);
        btn = findViewById(R.id.btn);

        btn.setOnClickListener(btnListener);
    }

    View.OnClickListener btnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle("사용자 정보 입력");
            dialog.setIcon(R.drawable.star);

            // 💡 이미지에서 새로 추가된 커스텀 뷰 설정 부분입니다.
            View dialogView = View.inflate(MainActivity.this, R.layout.dialog, null);
            dialog.setView(dialogView);
            dialog.setPositiveButton("입력완료", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(editName == null)
                        editName = dialogView.findViewById(R.id.dialog_edit_name);
                    if(editEmail == null)
                      editEmail = dialogView.findViewById(R.id.dialog_edit_email);

                    textName.setText("성명: " +editName.getText().toString());
                    textEmail.setText("이메일: " + editEmail.getText().toString());
                }
            });
            dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = new Toast(MainActivity.this);
                    View toastView = View.inflate(MainActivity.this, R.layout.toast, null);
                    toast.setView(toastView);
                    toast.show();
                }
            });
            dialog.show();

        }
    };
}