package firstlook.jihad.zoornahotel.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import firstlook.jihad.zoornahotel.Adapter.RoomAdapter;
import firstlook.jihad.zoornahotel.R;
import firstlook.jihad.zoornahotel.models.Room;

public class RoomActivity extends AppCompatActivity implements RoomAdapter.RoomClickListener {
    private RecyclerView recyclerView;
    private RoomAdapter roomAdapter;
    private List<Room> roomList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize room list
        roomList = new ArrayList<>();
        // Add sample rooms (replace with your own data)
        roomList.add(new Room("101", "Single Room", 50.0));
        roomList.add(new Room("201", "Double Room", 70.0));
        roomList.add(new Room("301", "Suite", 120.0));

        // Initialize RoomAdapter
        roomAdapter = new RoomAdapter(roomList, this);

        // Set RoomClickListener to handle room selection
        roomAdapter.setRoomClickListener(this);

        // Set adapter to RecyclerView
        recyclerView.setAdapter(roomAdapter);
    }

    @Override
    public void onRoomClick(Room room) {
        // Handle room selection
        Intent intent = new Intent(RoomActivity.this, ReservationActivity.class);
        intent.putExtra("room", room);
        startActivity(intent);
    }
}
