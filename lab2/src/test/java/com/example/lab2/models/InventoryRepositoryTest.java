package com.example.lab2.models;

import com.example.lab2.Lab2Application;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Lab2Application.class)
@Sql(scripts= "/plants-dataset.sql")
@DirtiesContext(classMode=DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InventoryRepositoryTest {
    @Autowired
    PlantInventoryEntryRepository plantInventoryEntryRepo;
    @Autowired
    PlantInventoryItemRepository plantInventoryItemRepo;
    @Autowired
    PlantReservationRepository plantReservationRepo;
    @Autowired
    InventoryRepository inventoryRepo;
    @Test
    public void queryPlantCatalog() {
        assertThat(plantInventoryEntryRepo.count()).isEqualTo(14l);
    }
    @Test
    public void queryByName() {
        assertThat(plantInventoryEntryRepo.findByNameContaining("Mini").size()).isEqualTo(2);
    }
    @Test
    public void findAvailableTest() {
        PlantInventoryEntry entry = plantInventoryEntryRepo.findById(1l).orElse(null);
        PlantInventoryItem item = plantInventoryItemRepo.findOneByPlantInfo(entry);
        Assertions.assertThat(inventoryRepo.findAvailablePlants("Mini",
                        LocalDate.of(2020,2,20), LocalDate.of(2020,2,25)))
                .contains(entry);
        PlantReservation po = new PlantReservation();
        po.setPlant(item);
        po.setSchedule(BusinessPeriod.of(LocalDate.of(2017, 2, 20),
                LocalDate.of(2017, 2, 25)));
        plantReservationRepo.save(po);
        Assertions.assertThat(inventoryRepo.findAvailablePlants("Mini",
                        LocalDate.of(2020,2,20), LocalDate.of(2020,2,25)))
                .contains(entry);
    }
}

