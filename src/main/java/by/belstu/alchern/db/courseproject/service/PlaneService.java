package by.belstu.alchern.db.courseproject.service;

import by.belstu.alchern.db.courseproject.model.impl.Plane;
import by.belstu.alchern.db.courseproject.service.exception.PlaneServiceException;

import java.util.List;

public interface PlaneService {
    List<Plane> getAll() throws PlaneServiceException;
}
