package threeadd.glimpsy.feature.nonchest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import threeadd.glimpsy.Glimpsy;
import threeadd.glimpsy.util.SerializationUtil;
import threeadd.glimpsy.util.persistence.Repository;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class NonChestRepository extends Repository<NonChest> {

    private final static NonChestRepository instance = new NonChestRepository();
    private static final Logger log = LoggerFactory.getLogger(NonChestRepository.class);

    public static NonChestRepository getInstance() {
        return instance;
    }

    @Override
    protected void createTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS non_chest (" +
                    "slot INTEGER PRIMARY KEY," +
                    "item_stack TEXT NOT NULL)");
        }
    }

    public void save(NonChest chest) {
        String sql = "REPLACE INTO non_chest (slot, item_stack) VALUES (?, ?)";

        try (Connection conn = Glimpsy.getDataBase().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            ItemStack[] contents = chest.getInventory().getContents();
            for (int slot = 0; slot < contents.length; slot++) {
                ItemStack item = contents[slot];
                if (item == null || item.getType().equals(Material.AIR))
                    continue;

                String json = SerializationUtil.itemStackToJson(item);
                ps.setInt(1, slot);
                ps.setString(2, json);
                ps.addBatch();
            }

            ps.executeBatch();
            conn.commit(); // Transaction end
            conn.setAutoCommit(true);

        } catch (SQLException e) {
            log.error("SQL Failed", e);
        }
    }

    public NonChest load() {
        String sql = "SELECT * FROM non_chest";
        Map<Integer, ItemStack> items = new HashMap<>();

        try (Connection conn = Glimpsy.getDataBase().getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int slot = rs.getInt("slot");
                String json = rs.getString("item_stack");
                ItemStack item = SerializationUtil.itemStackFromJson(json);

                items.put(slot, item);
            }
        } catch (SQLException e) {
            log.error("SQL Failed", e);
        }

        return new NonChest(items);
    }
}
