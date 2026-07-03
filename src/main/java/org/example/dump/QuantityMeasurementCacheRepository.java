//package org.example.repository;
//
//import org.example.model.QuantityMeasurementEntity;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//import org.example.model.OperationType;
//public class QuantityMeasurementCacheRepository
//        implements IQuantityMeasurementRepository {
//
//    private static
//    QuantityMeasurementCacheRepository
//            instance;
//
//    private final List<QuantityMeasurementEntity>
//            cache;
//
//    private QuantityMeasurementCacheRepository() {
//
//        cache = new ArrayList<>();
//    }
//
//    public static
//    QuantityMeasurementCacheRepository
//    getInstance() {
//
//        if (instance == null) {
//
//            instance =
//                    new QuantityMeasurementCacheRepository();
//        }
//
//        return instance;
//    }
//
//    // ===========================
//    // UC15
//    // ===========================
//
//    @Override
//    public void save(
//
//            QuantityMeasurementEntity entity
//    ) {
//
//        cache.add(entity);
//    }
//
//    @Override
//    public List<QuantityMeasurementEntity>
//    findAll() {
//
//        return new ArrayList<>(cache);
//    }
//
//    // ===========================
//    // UC16
//    // ===========================
//
//    @Override
//    public List<QuantityMeasurementEntity>
//    getAllMeasurements() {
//
//        return findAll();
//    }
//
//
//    public List<QuantityMeasurementEntity>
//    getMeasurementsByOperation(
//
//            OperationType operation
//    ) {
//
//        return cache.stream()
//
//                .filter(entity ->
//
//                        entity.getOperation() == operation
//                )
//
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<QuantityMeasurementEntity>
//    getMeasurementsByType(
//
//            String measurementType
//    ) {
//
//        return cache.stream()
//
//                .filter(entity ->
//
//                        entity.getMeasurementType()
//
//                                .equalsIgnoreCase(
//
//                                        measurementType
//                                )
//                )
//
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteAll() {
//
//        cache.clear();
//    }
//
//    @Override
//    public int getTotalCount() {
//
//        return cache.size();
//    }
//}