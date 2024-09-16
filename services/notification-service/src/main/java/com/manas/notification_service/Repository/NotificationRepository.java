package com.manas.notification_service.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.manas.notification_service.Entity.Notification;

public interface NotificationRepository extends MongoRepository<Notification,String> {
    
}
