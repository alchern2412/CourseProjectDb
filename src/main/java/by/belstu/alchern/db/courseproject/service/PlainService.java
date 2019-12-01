package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Plain;
import by.belstu.alchern.db.courseproject.service.exception.PlainServiceException;

import java.util.List;

public interface PlainService {
    List<Plain> getAll() throws PlainServiceException;
}
