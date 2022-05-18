package com.example.medicalsystem.singleliveevent;

import androidx.lifecycle.Observer;

import org.jetbrains.annotations.NotNull;

public class EventObserver<T> implements Observer<Event<T>> {
    private EventHandler<T> onEventUnhandledContent;

    public EventObserver(@NotNull EventHandler<T> onEventUnhandledContent) {
        this.onEventUnhandledContent = onEventUnhandledContent;
    }

    @Override
    public void onChanged(Event<T> event) {
        if(event != null){
            onEventUnhandledContent.onEventUnHandled(event.getContentIfNotHandled());
        }
    }

}