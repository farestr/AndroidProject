package firstlook.jihad.zoornahotel.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import firstlook.jihad.zoornahotel.Activity.RoomActivity;
import firstlook.jihad.zoornahotel.R;
import firstlook.jihad.zoornahotel.models.Room;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {
    private List<Room> roomList;
    private RoomClickListener roomClickListener;

    public interface RoomClickListener {
        void onRoomClick(Room room);
    }

    public RoomAdapter(List<Room> roomList, RoomActivity roomActivity) {
        this.roomList = roomList;
    }

    public void setRoomClickListener(RoomClickListener roomClickListener) {
        this.roomClickListener = roomClickListener;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.bind(room);
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView roomNumberTextView;
        private TextView roomTypeTextView;
        private TextView priceTextView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            roomNumberTextView = itemView.findViewById(R.id.roomNumberTextView);
            roomTypeTextView = itemView.findViewById(R.id.roomTypeTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);

            itemView.setOnClickListener(this);
        }

        public void bind(Room room) {
            roomNumberTextView.setText(room.getRoomNumber());
            roomTypeTextView.setText(room.getRoomType());
            priceTextView.setText(String.format("$%.2f", room.getPrice()));
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION && roomClickListener != null) {
                Room room = roomList.get(position);
                roomClickListener.onRoomClick(room);
            }
        }
    }
}
