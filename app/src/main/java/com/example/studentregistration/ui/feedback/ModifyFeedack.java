package com.example.studentregistration.ui.feedback;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ModifyFeedack {
     private DatabaseReference databaseReference;
     public ModifyFeedack(){
         FirebaseDatabase db = FirebaseDatabase.getInstance();
         databaseReference = db.getReference(FeedbackDetail.class.getSimpleName());
     }
     public Task<Void> add(FeedbackDetail fd)
     {
        return databaseReference.child(fd.getFeedback()).push().setValue(fd);
     }
}
