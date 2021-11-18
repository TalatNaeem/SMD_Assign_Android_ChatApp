package com.talat.i170213_i170208;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PhoneFragment extends Fragment {

    List<CallRecord> callList = null;
    RecyclerView recyclerViewCall;
    View view;
    String[] times = {"10:21", "22:14", "Mon", "Wed"};
    String[] statuses = {"inbound", "lost"};
    int counter = 0;
    CallHistoryAdapter adapter;

    public PhoneFragment() {
        if (callList == null) {
            callList = new ArrayList<>();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_phone, container, false);

        recyclerViewCall = view.findViewById(R.id.CallList);
        checkPermission();

        return view;
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(view.getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[] { Manifest.permission.READ_CONTACTS}, 100);
        }
        else {
            getContactList();
        }
    }

    private void getContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String sort = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC";
        Context applicationContext = Home.getContextOfApplication();
        Cursor cursor = applicationContext.getContentResolver().query(uri, null , null, null , sort);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                if (counter == 10) {
                    break;
                }
                String id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                Uri uriPhone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
                String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?";
                Cursor phoneCursor = applicationContext.getContentResolver().query(uriPhone, null, selection, new String[]{id}, null);
                if (phoneCursor.moveToNext()) {
                    String number = phoneCursor.getString(phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    Random r = new Random();
                    int randomTime = r.nextInt(times.length);
                    int randomStatus = r.nextInt(statuses.length);
                    CallRecord model = new CallRecord(name, times[randomTime], statuses[randomStatus], R.drawable.no_dp);
                    callList.add(model);
                    phoneCursor.close();
                }
                counter++;
            }
            cursor.close();
        }
        recyclerViewCall.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new CallHistoryAdapter(callList, view.getContext());
        recyclerViewCall.setAdapter(adapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getContactList();
        }
        else {
            Toast.makeText(view.getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }
}