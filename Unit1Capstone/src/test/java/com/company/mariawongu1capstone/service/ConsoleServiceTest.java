package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doReturn;

public class ConsoleServiceTest {

    ConsoleService consoleService;

    ConsoleDao consoleDao;

    @Before
    public void setUp() throws Exception {

        // Configures mock objects
        setUpConsoleDaoMock();

        // Passes mock objects
        consoleService = new ConsoleService(consoleDao);

    }

    // tests saveConsole() and findConsoleById()
    @Test
    public void saveFindConsole() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemoryAmount("4GB");
        consoleVM.setProcessor("NVIDIA");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleVM = consoleService.saveConsole(consoleVM);

        ConsoleViewModel fromService = consoleService.findConsoleById(consoleVM.getConsoleId());
        assertEquals(consoleVM, fromService);

    }

    // tests findAllConsoles()
    @Test
    public void findAllConsoles() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();

        consoleVM.setModel("Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemoryAmount("4GB");
        consoleVM.setProcessor("NVIDIA");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleService.saveConsole(consoleVM);

        consoleVM = new ConsoleViewModel();

        consoleVM.setModel("XBox One X");
        consoleVM.setManufacturer("Microsoft");
        consoleVM.setMemoryAmount("1 TB");
        consoleVM.setProcessor("8 Core AMD");
        consoleVM.setPrice(new BigDecimal(362.00).setScale(2));
        consoleVM.setQuantity(100);

        consoleService.saveConsole(consoleVM);

        List<ConsoleViewModel> fromService = consoleService.findAllConsoles();

        assertEquals(2, fromService.size());

    }

    // tests findConsolesByManufacturer()
    @Test
    public void findConsolesByManufacturer() {

        ConsoleViewModel consoleVM = new ConsoleViewModel();
        consoleVM.setModel("Switch");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemoryAmount("4GB");
        consoleVM.setProcessor("NVIDIA");
        consoleVM.setPrice(new BigDecimal(100.00).setScale(2));
        consoleVM.setQuantity(10);

        consoleService.saveConsole(consoleVM);

        consoleVM = new ConsoleViewModel();
        consoleVM.setModel("XBox One X");
        consoleVM.setManufacturer("Microsoft");
        consoleVM.setMemoryAmount("1 TB");
        consoleVM.setProcessor("8 Core AMD");
        consoleVM.setPrice(new BigDecimal(362.00).setScale(2));
        consoleVM.setQuantity(100);

        consoleService.saveConsole(consoleVM);

        consoleVM = new ConsoleViewModel();
        consoleVM.setModel("2DS XL");
        consoleVM.setManufacturer("Nintendo");
        consoleVM.setMemoryAmount("4GB");
        consoleVM.setProcessor("Quad-Core");
        consoleVM.setPrice(new BigDecimal(189.00).setScale(2));
        consoleVM.setQuantity(100);

        consoleService.saveConsole(consoleVM);

        List<ConsoleViewModel> cList = consoleService.findConsolesByManufacturer("Nintendo");
        assertEquals(2, cList.size());
        assertEquals("Nintendo", cList.get(0).getManufacturer());

        cList = consoleService.findConsolesByManufacturer("Microsoft");
        assertEquals(1, cList.size());
        assertEquals("Microsoft", cList.get(0).getManufacturer());

        cList = consoleService.findConsolesByManufacturer("Sony");
        assertEquals(0, cList.size());

    }

    // Create mocks

    public void setUpConsoleDaoMock() {

        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);

        Console console = new Console();
        console.setModel("Switch");
        console.setManufacturer("Nintendo");
        console.setMemoryAmount("4GB");
        console.setProcessor("NVIDIA");
        console.setPrice(new BigDecimal(100.00).setScale(2));
        console.setQuantity(10);

        Console console2 = new Console();
        console2.setConsoleId(1);
        console2.setModel("Switch");
        console2.setManufacturer("Nintendo");
        console2.setMemoryAmount("4GB");
        console2.setProcessor("NVIDIA");
        console2.setPrice(new BigDecimal(100.00).setScale(2));
        console2.setQuantity(10);

        Console console3 = new Console();
        console3.setModel("XBox One X");
        console3.setManufacturer("Microsoft");
        console3.setMemoryAmount("1 TB");
        console3.setProcessor("8 Core AMD");
        console3.setPrice(new BigDecimal(362.00).setScale(2));
        console3.setQuantity(100);

        Console console4 = new Console();
        console4.setConsoleId(2);
        console4.setModel("XBox One X");
        console4.setManufacturer("Microsoft");
        console4.setMemoryAmount("1 TB");
        console4.setProcessor("8 Core AMD");
        console4.setPrice(new BigDecimal(362.00).setScale(2));
        console4.setQuantity(100);

        Console console5 = new Console();
        console5.setModel("2DS XL");
        console5.setManufacturer("Nintendo");
        console5.setMemoryAmount("4GB");
        console5.setProcessor("Quad-Core");
        console5.setPrice(new BigDecimal(189.00).setScale(2));
        console5.setQuantity(100);

        Console console6 = new Console();
        console6.setConsoleId(3);
        console6.setModel("2DS XL");
        console6.setManufacturer("Nintendo");
        console6.setMemoryAmount("4GB");
        console6.setProcessor("Quad-Core");
        console6.setPrice(new BigDecimal(189.00).setScale(2));
        console6.setQuantity(100);

        List<Console> consolesList = new ArrayList<>();
        consolesList.add(console2);
        consolesList.add(console4);

        List<Console> nintendoList = new ArrayList<>();
        nintendoList.add(console2);
        nintendoList.add(console6);

        List<Console> microsoftList = new ArrayList<>();
        microsoftList.add(console4);

        List<Console> emptyList = new ArrayList<>();

        doReturn(console2).when(consoleDao).addConsole(console);
        doReturn(console4).when(consoleDao).addConsole(console3);
        doReturn(console6).when(consoleDao).addConsole(console5);
        doReturn(console2).when(consoleDao).getConsole(1);
        doReturn(consolesList).when(consoleDao).getAllConsoles();
        doReturn(nintendoList).when(consoleDao).findConsolesByManufacturer("Nintendo");
        doReturn(microsoftList).when(consoleDao).findConsolesByManufacturer("Microsoft");
        doReturn(emptyList).when(consoleDao).findConsolesByManufacturer("Sony");

    }

}
