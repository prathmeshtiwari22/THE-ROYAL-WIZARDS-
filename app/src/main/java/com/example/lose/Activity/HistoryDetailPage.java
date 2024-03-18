package com.example.lose.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import com.example.lose.R;

public class HistoryDetailPage extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView itemName;
    private TextView foundAddress;
    private TextView dateFound;
    private TextView postDate;
    private TextView pickUpContact;
    private TextView pickUpAddress;
    private TextView itemDesc;
    private ImageView itemImage;
    private TextView itemReturnedTextView;
    private Button returnBtn;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String itemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail_page);

        initializedUI();
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("History Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle itemData = getIntent().getExtras();
        String Title = itemData.getString("Title");
        String PostDate = itemData.getString("PostDate");
        String FoundAddress = itemData.getString("FoundAddress");
        String DateFound = itemData.getString("DateFound");
        String PickUpContact = itemData.getString("PickUpContact");
        String PickUpAddress = itemData.getString("PickUpAddress");
        String ItemDesc = itemData.getString("ItemDesc");
        String ImageUrl = itemData.getString("ImageUrl");
        boolean ItemStatus = itemData.getBoolean("ItemStatus");
        itemID = itemData.getString("ItemID");

        itemName.setText(Title);
        foundAddress.setText(FoundAddress);
        dateFound.setText(DateFound);
        postDate.setText(PostDate);
        pickUpContact.setText(PickUpContact);
        pickUpAddress.setText(PickUpAddress);
        itemDesc.setText(ItemDesc);
        Picasso.get().load(ImageUrl).into(itemImage);

        if (!ItemStatus) {
            returnBtn.setVisibility(View.GONE);
            itemReturnedTextView.setVisibility(View.VISIBLE);
        }
    }

    public void returnBtnOnClicked(View v) {
        returnBtn.setVisibility(View.GONE);
        myRef.child(itemID).child("itemStatus").setValue(false);
        itemReturnedTextView.setVisibility(View.VISIBLE);
    }

    private void initializedUI() {
        toolbar = findViewById(R.id.actionBar);
        itemName = findViewById(R.id.itemNameTextView);
        foundAddress = findViewById(R.id.foundAddressTextView);
        dateFound = findViewById(R.id.dateFoundTextView);
        postDate = findViewById(R.id.postDateTextView);
        pickUpContact = findViewById(R.id.pickUpContactTextView);
        pickUpAddress = findViewById(R.id.pickUpAddressTextView);
        itemDesc = findViewById(R.id.descriptionTextView);
        itemImage = findViewById(R.id.itemImage);
        itemReturnedTextView = findViewById(R.id.itemReturnedTextView);
        returnBtn = findViewById(R.id.returnBtn);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Lost Items");
    }
}
