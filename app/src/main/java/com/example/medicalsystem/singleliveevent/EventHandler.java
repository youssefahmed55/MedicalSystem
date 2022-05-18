package com.example.medicalsystem.singleliveevent;

public interface EventHandler<V>{
    void onEventUnHandled(V object);
}
