
--Insert into Depot (depot_name, DEPOT_BUSCAPACITY) VALUES ('Depot1', 50),('Depot2', 50);

--Insert into BusVehicule (busVehicule_plateNumber, busVehicule_type, busVehicule_color, busVehicule_passengerCapacity) VALUES ('BUS-001-001', 0, 0, 30), ('BUS-002-001', 0, 0, 30), ('BUS-000-001', 0, 0, 30);

--Update BusVehicule SET FK_depotId = (SELECT id FROM Depot WHERE depot_name = 'Depot1') WHERE busVehicule_plateNumber = 'BUS-001-001';
--Update BusVehicule SET FK_depotId = (SELECT id FROM Depot WHERE depot_name = 'Depot2') WHERE busVehicule_plateNumber = 'BUS-002-001';