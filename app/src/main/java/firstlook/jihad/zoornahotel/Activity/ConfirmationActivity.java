package firstlook.jihad.zoornahotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import firstlook.jihad.zoornahotel.R;

public class ConfirmationActivity extends AppCompatActivity {

    private TextView confirmationTextView;
    private Button closeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        confirmationTextView = findViewById(R.id.confirmationTextView);
        closeButton = findViewById(R.id.closeButton);

        // Get the confirmation message from the intent
        Intent intent = getIntent();
        String confirmationMessage = intent.getStringExtra("confirmationMessage");

        // Set the confirmation message
        confirmationTextView.setText(confirmationMessage);

        // Set click listener for the close button
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close the activity
                finish();
            }
        });
    }
}
