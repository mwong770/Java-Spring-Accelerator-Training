package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;

import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.TShirtViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtServiceTest {

    TShirtService tShirtService;

    TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {

        // Configures mock objects
        setUpTShirtDaoMock();

        // Passes mock objects
        tShirtService = new TShirtService(tShirtDao);
    }

    @Test
    public void saveFindTShirt()
    {
        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("I Turn Coffee Into Code T-Shirt");
        tShirtVM.setPrice(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtVM = tShirtService.saveTShirt(tShirtVM);

        TShirtViewModel fromService  = tShirtService.findTShirtById(tShirtVM.gettShirtId());

        assertEquals(tShirtVM, fromService);
    }

    @Test
    public void findAllTShirts() {

        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("I Turn Coffee Into Code T-Shirt");
        tShirtVM.setPrice(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("Can't Hear You I'm Gaming T-Shirt");
        tShirtVM.setPrice(new BigDecimal(12.95).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> fromService = tShirtService.findAllTShirts();

        assertEquals(2, fromService.size());

    }

    @Test
    public void findTShirtsByColor() {

        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("I Turn Coffee Into Code T-Shirt");
        tShirtVM.setPrice(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("Can't Hear You I'm Gaming T-Shirt");
        tShirtVM.setPrice(new BigDecimal(12.95).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("medium");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("Gamer Nutritional Facts Novelty Video Game Lover T-Shirt");
        tShirtVM.setPrice(new BigDecimal(15.95).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> tList = tShirtService.findTShirtsByColor("black");
        assertEquals(2,tList.size());
        assertEquals("black", tList.get(0).getColor());

        tList = tShirtService.findTShirtsByColor("blue");
        assertEquals(1,tList.size());
        assertEquals("blue", tList.get(0).getColor());

        tList = tShirtService.findTShirtsByColor("red");
        assertEquals(0,tList.size());

    }
    
    @Test
    public void findTShirtsBySize() {

        TShirtViewModel tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("blue");
        tShirtVM.setDescription("I Turn Coffee Into Code T-Shirt");
        tShirtVM.setPrice(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("small");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("Can't Hear You I'm Gaming T-Shirt");
        tShirtVM.setPrice(new BigDecimal(12.95).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        tShirtVM = new TShirtViewModel();
        tShirtVM.setSize("medium");
        tShirtVM.setColor("black");
        tShirtVM.setDescription("Gamer Nutritional Facts Novelty Video Game Lover T-Shirt");
        tShirtVM.setPrice(new BigDecimal(15.95).setScale(2, RoundingMode.HALF_UP));
        tShirtVM.setQuantity(100);

        tShirtService.saveTShirt(tShirtVM);

        List<TShirtViewModel> tList = tShirtService.findTShirtsBySize("small");
        assertEquals(2,tList.size());
        assertEquals("small", tList.get(0).getSize());

        tList = tShirtService.findTShirtsBySize("medium");
        assertEquals(1,tList.size());
        assertEquals("medium", tList.get(0).getSize());

        tList = tShirtService.findTShirtsBySize("large");
        assertEquals(0,tList.size());

    }

    // Helper method

    public void setUpTShirtDaoMock() {

        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);

        TShirt tShirt = new TShirt();
        tShirt.setSize("small");
        tShirt.setColor("blue");
        tShirt.setDescription("I Turn Coffee Into Code T-Shirt");
        tShirt.setPrice(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP));
        tShirt.setQuantity(100);

        TShirt tShirt2 = new TShirt();
        tShirt2.settShirtId(1);
        tShirt2.setSize("small");
        tShirt2.setColor("blue");
        tShirt2.setDescription("I Turn Coffee Into Code T-Shirt");
        tShirt2.setPrice(new BigDecimal(19.99).setScale(2, RoundingMode.HALF_UP));
        tShirt2.setQuantity(100);

        TShirt tShirt3 = new TShirt();
        tShirt3.setSize("small");
        tShirt3.setColor("black");
        tShirt3.setDescription("Can't Hear You I'm Gaming T-Shirt");
        tShirt3.setPrice(new BigDecimal(12.95).setScale(2, RoundingMode.HALF_UP));
        tShirt3.setQuantity(100);

        TShirt tShirt4 = new TShirt();
        tShirt4.settShirtId(2);
        tShirt4.setSize("small");
        tShirt4.setColor("black");
        tShirt4.setDescription("Can't Hear You I'm Gaming T-Shirt");
        tShirt4.setPrice(new BigDecimal(12.95).setScale(2, RoundingMode.HALF_UP));
        tShirt4.setQuantity(100);

        TShirt tShirt5 = new TShirt();
        tShirt5.setSize("medium");
        tShirt5.setColor("black");
        tShirt5.setDescription("Gamer Nutritional Facts Novelty Video Game Lover T-Shirt");
        tShirt5.setPrice(new BigDecimal(15.95).setScale(2, RoundingMode.HALF_UP));
        tShirt5.setQuantity(100);

        TShirt tShirt6 = new TShirt();
        tShirt6.settShirtId(3);
        tShirt6.setSize("medium");
        tShirt6.setColor("black");
        tShirt6.setDescription("Gamer Nutritional Facts Novelty Video Game Lover T-Shirt");
        tShirt6.setPrice(new BigDecimal(15.95).setScale(2, RoundingMode.HALF_UP));
        tShirt6.setQuantity(100);

        List<TShirt> tShirtsList = new ArrayList<>();
        tShirtsList.add(tShirt2);
        tShirtsList.add(tShirt4);

        List<TShirt> blackList = new ArrayList<>();
        blackList.add(tShirt4);
        blackList.add(tShirt6);

        List<TShirt> blueList = new ArrayList<>();
        blueList.add(tShirt2);

        List<TShirt> smallList = new ArrayList<>();
        smallList.add(tShirt2);
        smallList.add(tShirt4);

        List<TShirt> mediumList = new ArrayList<>();
        mediumList.add(tShirt6);

        List<TShirt> emptyList = new ArrayList<>();

        doReturn(tShirt2).when(tShirtDao).addTShirt(tShirt);
        doReturn(tShirt4).when(tShirtDao).addTShirt(tShirt3);
        doReturn(tShirt6).when(tShirtDao).addTShirt(tShirt5);
        doReturn(tShirt2).when(tShirtDao).getTShirt(1);
        doReturn(tShirtsList).when(tShirtDao).getAllTShirts();

        doReturn(blackList).when(tShirtDao).findTShirtsByColor("black");
        doReturn(blueList).when(tShirtDao).findTShirtsByColor("blue");
        doReturn(emptyList).when(tShirtDao).findTShirtsByColor("red");

        doReturn(smallList).when(tShirtDao).findTShirtsBySize("small");
        doReturn(mediumList).when(tShirtDao).findTShirtsBySize("medium");
        doReturn(emptyList).when(tShirtDao).findTShirtsBySize("large");

    }

}
