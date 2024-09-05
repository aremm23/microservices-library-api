package com.artsem.api.libraryservice.service;

import com.artsem.api.libraryservice.model.BookIdMessage;
import com.artsem.api.libraryservice.service.converter.MessageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitReceiver {

    private final MessageConverter<BookIdMessage> bookIdMessageConverter;

    private final BookStatusService bookStatusService;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(String message) {
        if (isBookIdMessage(message)) {
            BookIdMessage bookIdMessage = bookIdMessageConverter.convert(message);
            processBookIdMessage(bookIdMessage);
        } else {
            throw new IllegalArgumentException("Unknown message type");
        }
    }

    private boolean isBookIdMessage(String message) {
        return message.contains("id");
    }

    private void processBookIdMessage(BookIdMessage bookIdPojo) {
        bookStatusService.addNewFreeBook(bookIdPojo.getId());
    }
}
