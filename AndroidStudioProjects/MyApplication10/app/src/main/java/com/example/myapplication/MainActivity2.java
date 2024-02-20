package com.example.myapplication;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.databinding.ActivityMain2Binding;
import com.example.myapplication.databinding.ActivityMainBinding;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;
import java.util.Calendar;
public class MainActivity2 extends AppCompatActivity {
    private ActivityMain2Binding binding; // ривязка к просмотру
    private MaterialTimePicker picker;    private Calendar calendar;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);
        binding = ActivityMain2Binding.inflate(getLayoutInflater()); // привязка к просмотру
        setContentView(binding.getRoot()); // создаем метод для канала уведомлений
        createNotificationChannel();
        binding.selectTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    showTimePicker(); // вызывает метод показа часов
            }
        });
        binding.setAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAlarm(); // устанавливает уведомление
            }
        });
        binding.cancelAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm(); // скрывает уведомление
            }
        });
    }
    private void cancelAlarm() {
        Intent intent = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_IMMUTABLE);
        if(alarmManager == null){
            alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        }
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this,"Напоминание удалено",Toast.LENGTH_SHORT).show();
    }
    private void setAlarm() {
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this,AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent, PendingIntent.FLAG_IMMUTABLE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY,pendingIntent);
        Toast.makeText(this,"Напоминание добавлено",Toast.LENGTH_SHORT).show();
    }
    private void showTimePicker() {
        picker = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(13)
                .setMinute(0)
                .setTitleText("Select Alarm Time")
                .build();
        picker.show(getSupportFragmentManager(),"foxandroid");
        picker.addOnPositiveButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picker.getHour() > 12){
                    binding.selectedTime.setText(
                            String.format("%02d",(picker.getHour()-12))+" : "+String.format("%02d",picker.getMinute())+" PM"                    );
                }else {
                    binding.selectedTime.setText(picker.getHour()+" : " + picker.getMinute() + " AM");
                }
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY,picker.getHour());
                calendar.set(Calendar.MINUTE,picker.getMinute());
                calendar.set(Calendar.SECOND,0);
                calendar.set(Calendar.MILLISECOND,0);
            }
        });
    }
    private void
    createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "foxandroidReminderChannel"; //название канала
            String description = "Channel For Alarm Manager"; // описание канала
            int importance = NotificationManager.IMPORTANCE_HIGH; // IMPORTANCE_HIGH - информация отображается влюбом случае
            NotificationChannel channel = new NotificationChannel("foxandroid",name,importance);
            channel.setDescription(description);
//            NotificationManager notificationManager = getSystemService(NotificationManager.class);
//            notificationManager.createNotificationChannel(channel);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}