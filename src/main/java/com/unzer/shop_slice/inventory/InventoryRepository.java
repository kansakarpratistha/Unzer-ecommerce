package com.unzer.shop_slice.inventory;

import java.time.Instant;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InventoryRepository {
    private final JdbcTemplate jdbcTemplate;
    public InventoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void insert(Long productId, int availableQuantity, int reservedQuantity, Instant createdAt, Instant lastUpdated) {
        String sql = "INSERT INTO inventory (product_id, available_quantity, reserved_quantity, created_at, last_updated) " +
                     "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, productId, availableQuantity, reservedQuantity, createdAt, lastUpdated);
    }

    public boolean tryReserve(Long productId, int quantity) {
        String sql = "UPDATE inventory SET reserved_quantity = reserved_quantity + ?, last_updated = NOW() " +
                     "WHERE product_id = ? AND available_quantity - reserved_quantity >= ?";
        int rowsAffected = jdbcTemplate.update(sql, quantity, productId, quantity);
        return rowsAffected > 0;
    }

    public void releaseReservation(Long productId, int quantity) {
        String sql = "UPDATE inventory SET reserved_quantity = reserved_quantity - ?, last_updated = NOW() " +
                     "WHERE product_id = ? AND reserved_quantity >= ?";
        jdbcTemplate.update(sql, quantity, productId, quantity);
    }

    public void commitReservation(Long productId, int quantity) {
        String sql = "UPDATE inventory SET available_quantity = available_quantity - ?, reserved_quantity = reserved_quantity - ?, last_updated = NOW() " +
                     "WHERE product_id = ?";
        jdbcTemplate.update(sql, quantity, quantity, productId);
    }

    public Inventory findById(Long productId) {
        String sql = "SELECT * FROM inventory WHERE product_id = ?";
        return jdbcTemplate.queryForObject(
            sql, (rs, rowNum) -> 
                new Inventory(
                    rs.getLong("product_id"),
                    rs.getInt("available_quantity"),
                    rs.getInt("reserved_quantity"),
                    rs.getTimestamp("created_at").toInstant(),
                    rs.getTimestamp("last_updated").toInstant()
                ),
            productId
        );
    }
}
