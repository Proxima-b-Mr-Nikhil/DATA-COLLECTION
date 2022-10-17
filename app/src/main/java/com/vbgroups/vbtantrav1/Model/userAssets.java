package com.vbgroups.vbtantrav1.Model;

public class userAssets {
    private String Conductor_type;
    private  String Configuration;
    private  String Type;
    private String Asset_name;
    private  String uploaderid;
    private  String itemimage;
    private  String Load_type;
    private String XR_ratio;
    private String Rated_voltage;
    private String Short_circuit;
    private String Source_name;
    private String Coming_from;
    private String Make;
    private String Model;
    private String Rating;
    private String Primary_voltage;
    private String Secondary_voltage_1;
    private String Secondary_voltage_2;
    private String Z;
    private String Feeder_name;
    private String Transformer_name;
    private String Source_type;
    private String New_source_type;
    private String Xd;
    private String Generator_name;
    private String Remark;
    private String Voltage;
    private String Feeder_no;
    private String Maximum_voltage;
    private String Frame_rating;
    private String Trip;
    private String Plug;
    private String Ltpu;
    private String Ltd;
    private String Stpu;
    private String Std;
    private String Instantaneous;
    private String Primary_ct;
    private String Secondary_ct;
    private String l1;
    private String t1;
    private String l2;
    private String t2;
    private String l3;
    private String Insulation_type;
    private String Connected_from;
    private String Connected_to;
    private String Cable_size;
    private String No_of_runs;
    private String Cable_length;
    private String Cable_core;
    private String Cable_material;
    private String KW_rating;
    private String Motor_type;
    private String FLA_rating;
    private String Earthpit_maintenance;
    private String Physical_structure;
    private String Pit_surroundings;
    private String Earthpit_number;
    private String Earthpit_connectedto;
    private String Pit_to_ground_resistance;
    private String Pit_to_grid_resistance;
    private String Equipment_maintenance;
    private String Equipment_surroundings;
    private String Quality_index;
    private String Equipment_type;
    private String Type_of_hazard;
    private String Recommended_action;
    private String Capaciotr_id;
    private String Equipment_location;
    private String Equipment_number;
    private String Rated_kv;
    private String Kvar_bank;
    private String No_of_banks;
    private String Loading;
    private String Equipment_cable;

    public userAssets(String conductor_type, String configuration, String type, String asset_name, String uploaderid, String itemimage, String load_type, String XR_ratio, String rated_voltage, String short_circuit, String source_name, String coming_from, String make, String model, String rating, String primary_voltage, String secondary_voltage_1, String secondary_voltage_2, String z, String feeder_name, String transformer_name, String source_type, String new_source_type, String xd, String generator_name, String remark, String voltage, String feeder_no, String maximum_voltage, String frame_rating, String trip, String plug, String ltpu, String ltd, String stpu, String std, String instantaneous, String primary_ct, String secondary_ct, String l1, String t1, String l2, String t2, String l3, String insulation_type, String connected_from, String connected_to, String cable_size, String no_of_runs, String cable_length, String cable_core, String cable_material, String KW_rating, String motor_type, String FLA_rating, String earthpit_maintenance, String physical_structure, String pit_surroundings, String earthpit_number, String earthpit_connectedto, String pit_to_ground_resistance, String pit_to_grid_resistance, String equipment_maintenance, String equipment_surroundings, String quality_index, String equipment_type, String type_of_hazard, String recommended_action, String capaciotr_id, String equipment_location, String equipment_number, String rated_kv, String kvar_bank, String no_of_banks, String loading, String equipment_cable) {
        Conductor_type = conductor_type;
        Configuration = configuration;
        Type = type;
        Asset_name = asset_name;
        this.uploaderid = uploaderid;
        this.itemimage = itemimage;
        Load_type = load_type;
        this.XR_ratio = XR_ratio;
        Rated_voltage = rated_voltage;
        Short_circuit = short_circuit;
        Source_name = source_name;
        Coming_from = coming_from;
        Make = make;
        Model = model;
        Rating = rating;
        Primary_voltage = primary_voltage;
        Secondary_voltage_1 = secondary_voltage_1;
        Secondary_voltage_2 = secondary_voltage_2;
        Z = z;
        Feeder_name = feeder_name;
        Transformer_name = transformer_name;
        Source_type = source_type;
        New_source_type = new_source_type;
        Xd = xd;
        Generator_name = generator_name;
        Remark = remark;
        Voltage = voltage;
        Feeder_no = feeder_no;
        Maximum_voltage = maximum_voltage;
        Frame_rating = frame_rating;
        Trip = trip;
        Plug = plug;
        Ltpu = ltpu;
        Ltd = ltd;
        Stpu = stpu;
        Std = std;
        Instantaneous = instantaneous;
        Primary_ct = primary_ct;
        Secondary_ct = secondary_ct;
        this.l1 = l1;
        this.t1 = t1;
        this.l2 = l2;
        this.t2 = t2;
        this.l3 = l3;
        Insulation_type = insulation_type;
        Connected_from = connected_from;
        Connected_to = connected_to;
        Cable_size = cable_size;
        No_of_runs = no_of_runs;
        Cable_length = cable_length;
        Cable_core = cable_core;
        Cable_material = cable_material;
        this.KW_rating = KW_rating;
        Motor_type = motor_type;
        this.FLA_rating = FLA_rating;
        Earthpit_maintenance = earthpit_maintenance;
        Physical_structure = physical_structure;
        Pit_surroundings = pit_surroundings;
        Earthpit_number = earthpit_number;
        Earthpit_connectedto = earthpit_connectedto;
        Pit_to_ground_resistance = pit_to_ground_resistance;
        Pit_to_grid_resistance = pit_to_grid_resistance;
        Equipment_maintenance = equipment_maintenance;
        Equipment_surroundings = equipment_surroundings;
        Quality_index = quality_index;
        Equipment_type = equipment_type;
        Type_of_hazard = type_of_hazard;
        Recommended_action = recommended_action;
        Capaciotr_id = capaciotr_id;
        Equipment_location = equipment_location;
        Equipment_number = equipment_number;
        Rated_kv = rated_kv;
        Kvar_bank = kvar_bank;
        No_of_banks = no_of_banks;
        Loading = loading;
        Equipment_cable = equipment_cable;
    }

    public userAssets() {
    }

    public String getConductor_type() {
        return Conductor_type;
    }

    public void setConductor_type(String conductor_type) {
        Conductor_type = conductor_type;
    }

    public String getConfiguration() {
        return Configuration;
    }

    public void setConfiguration(String configuration) {
        Configuration = configuration;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getAsset_name() {
        return Asset_name;
    }

    public void setAsset_name(String asset_name) {
        Asset_name = asset_name;
    }

    public String getUploaderid() {
        return uploaderid;
    }

    public void setUploaderid(String uploaderid) {
        this.uploaderid = uploaderid;
    }

    public String getItemimage() {
        return itemimage;
    }

    public void setItemimage(String itemimage) {
        this.itemimage = itemimage;
    }

    public String getLoad_type() {
        return Load_type;
    }

    public void setLoad_type(String load_type) {
        Load_type = load_type;
    }

    public String getXR_ratio() {
        return XR_ratio;
    }

    public void setXR_ratio(String XR_ratio) {
        this.XR_ratio = XR_ratio;
    }

    public String getRated_voltage() {
        return Rated_voltage;
    }

    public void setRated_voltage(String rated_voltage) {
        Rated_voltage = rated_voltage;
    }

    public String getShort_circuit() {
        return Short_circuit;
    }

    public void setShort_circuit(String short_circuit) {
        Short_circuit = short_circuit;
    }

    public String getSource_name() {
        return Source_name;
    }

    public void setSource_name(String source_name) {
        Source_name = source_name;
    }

    public String getComing_from() {
        return Coming_from;
    }

    public void setComing_from(String coming_from) {
        Coming_from = coming_from;
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public String getPrimary_voltage() {
        return Primary_voltage;
    }

    public void setPrimary_voltage(String primary_voltage) {
        Primary_voltage = primary_voltage;
    }

    public String getSecondary_voltage_1() {
        return Secondary_voltage_1;
    }

    public void setSecondary_voltage_1(String secondary_voltage_1) {
        Secondary_voltage_1 = secondary_voltage_1;
    }

    public String getSecondary_voltage_2() {
        return Secondary_voltage_2;
    }

    public void setSecondary_voltage_2(String secondary_voltage_2) {
        Secondary_voltage_2 = secondary_voltage_2;
    }

    public String getZ() {
        return Z;
    }

    public void setZ(String z) {
        Z = z;
    }

    public String getFeeder_name() {
        return Feeder_name;
    }

    public void setFeeder_name(String feeder_name) {
        Feeder_name = feeder_name;
    }

    public String getTransformer_name() {
        return Transformer_name;
    }

    public void setTransformer_name(String transformer_name) {
        Transformer_name = transformer_name;
    }

    public String getSource_type() {
        return Source_type;
    }

    public void setSource_type(String source_type) {
        Source_type = source_type;
    }

    public String getNew_source_type() {
        return New_source_type;
    }

    public void setNew_source_type(String new_source_type) {
        New_source_type = new_source_type;
    }

    public String getXd() {
        return Xd;
    }

    public void setXd(String xd) {
        Xd = xd;
    }

    public String getGenerator_name() {
        return Generator_name;
    }

    public void setGenerator_name(String generator_name) {
        Generator_name = generator_name;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getVoltage() {
        return Voltage;
    }

    public void setVoltage(String voltage) {
        Voltage = voltage;
    }

    public String getFeeder_no() {
        return Feeder_no;
    }

    public void setFeeder_no(String feeder_no) {
        Feeder_no = feeder_no;
    }

    public String getMaximum_voltage() {
        return Maximum_voltage;
    }

    public void setMaximum_voltage(String maximum_voltage) {
        Maximum_voltage = maximum_voltage;
    }

    public String getFrame_rating() {
        return Frame_rating;
    }

    public void setFrame_rating(String frame_rating) {
        Frame_rating = frame_rating;
    }

    public String getTrip() {
        return Trip;
    }

    public void setTrip(String trip) {
        Trip = trip;
    }

    public String getPlug() {
        return Plug;
    }

    public void setPlug(String plug) {
        Plug = plug;
    }

    public String getLtpu() {
        return Ltpu;
    }

    public void setLtpu(String ltpu) {
        Ltpu = ltpu;
    }

    public String getLtd() {
        return Ltd;
    }

    public void setLtd(String ltd) {
        Ltd = ltd;
    }

    public String getStpu() {
        return Stpu;
    }

    public void setStpu(String stpu) {
        Stpu = stpu;
    }

    public String getStd() {
        return Std;
    }

    public void setStd(String std) {
        Std = std;
    }

    public String getInstantaneous() {
        return Instantaneous;
    }

    public void setInstantaneous(String instantaneous) {
        Instantaneous = instantaneous;
    }

    public String getPrimary_ct() {
        return Primary_ct;
    }

    public void setPrimary_ct(String primary_ct) {
        Primary_ct = primary_ct;
    }

    public String getSecondary_ct() {
        return Secondary_ct;
    }

    public void setSecondary_ct(String secondary_ct) {
        Secondary_ct = secondary_ct;
    }

    public String getL1() {
        return l1;
    }

    public void setL1(String l1) {
        this.l1 = l1;
    }

    public String getT1() {
        return t1;
    }

    public void setT1(String t1) {
        this.t1 = t1;
    }

    public String getL2() {
        return l2;
    }

    public void setL2(String l2) {
        this.l2 = l2;
    }

    public String getT2() {
        return t2;
    }

    public void setT2(String t2) {
        this.t2 = t2;
    }

    public String getL3() {
        return l3;
    }

    public void setL3(String l3) {
        this.l3 = l3;
    }

    public String getInsulation_type() {
        return Insulation_type;
    }

    public void setInsulation_type(String insulation_type) {
        Insulation_type = insulation_type;
    }

    public String getConnected_from() {
        return Connected_from;
    }

    public void setConnected_from(String connected_from) {
        Connected_from = connected_from;
    }

    public String getConnected_to() {
        return Connected_to;
    }

    public void setConnected_to(String connected_to) {
        Connected_to = connected_to;
    }

    public String getCable_size() {
        return Cable_size;
    }

    public void setCable_size(String cable_size) {
        Cable_size = cable_size;
    }

    public String getNo_of_runs() {
        return No_of_runs;
    }

    public void setNo_of_runs(String no_of_runs) {
        No_of_runs = no_of_runs;
    }

    public String getCable_length() {
        return Cable_length;
    }

    public void setCable_length(String cable_length) {
        Cable_length = cable_length;
    }

    public String getCable_core() {
        return Cable_core;
    }

    public void setCable_core(String cable_core) {
        Cable_core = cable_core;
    }

    public String getCable_material() {
        return Cable_material;
    }

    public void setCable_material(String cable_material) {
        Cable_material = cable_material;
    }

    public String getKW_rating() {
        return KW_rating;
    }

    public void setKW_rating(String KW_rating) {
        this.KW_rating = KW_rating;
    }

    public String getMotor_type() {
        return Motor_type;
    }

    public void setMotor_type(String motor_type) {
        Motor_type = motor_type;
    }

    public String getFLA_rating() {
        return FLA_rating;
    }

    public void setFLA_rating(String FLA_rating) {
        this.FLA_rating = FLA_rating;
    }

    public String getEarthpit_maintenance() {
        return Earthpit_maintenance;
    }

    public void setEarthpit_maintenance(String earthpit_maintenance) {
        Earthpit_maintenance = earthpit_maintenance;
    }

    public String getPhysical_structure() {
        return Physical_structure;
    }

    public void setPhysical_structure(String physical_structure) {
        Physical_structure = physical_structure;
    }

    public String getPit_surroundings() {
        return Pit_surroundings;
    }

    public void setPit_surroundings(String pit_surroundings) {
        Pit_surroundings = pit_surroundings;
    }

    public String getEarthpit_number() {
        return Earthpit_number;
    }

    public void setEarthpit_number(String earthpit_number) {
        Earthpit_number = earthpit_number;
    }

    public String getEarthpit_connectedto() {
        return Earthpit_connectedto;
    }

    public void setEarthpit_connectedto(String earthpit_connectedto) {
        Earthpit_connectedto = earthpit_connectedto;
    }

    public String getPit_to_ground_resistance() {
        return Pit_to_ground_resistance;
    }

    public void setPit_to_ground_resistance(String pit_to_ground_resistance) {
        Pit_to_ground_resistance = pit_to_ground_resistance;
    }

    public String getPit_to_grid_resistance() {
        return Pit_to_grid_resistance;
    }

    public void setPit_to_grid_resistance(String pit_to_grid_resistance) {
        Pit_to_grid_resistance = pit_to_grid_resistance;
    }

    public String getEquipment_maintenance() {
        return Equipment_maintenance;
    }

    public void setEquipment_maintenance(String equipment_maintenance) {
        Equipment_maintenance = equipment_maintenance;
    }

    public String getEquipment_surroundings() {
        return Equipment_surroundings;
    }

    public void setEquipment_surroundings(String equipment_surroundings) {
        Equipment_surroundings = equipment_surroundings;
    }

    public String getQuality_index() {
        return Quality_index;
    }

    public void setQuality_index(String quality_index) {
        Quality_index = quality_index;
    }

    public String getEquipment_type() {
        return Equipment_type;
    }

    public void setEquipment_type(String equipment_type) {
        Equipment_type = equipment_type;
    }

    public String getType_of_hazard() {
        return Type_of_hazard;
    }

    public void setType_of_hazard(String type_of_hazard) {
        Type_of_hazard = type_of_hazard;
    }

    public String getRecommended_action() {
        return Recommended_action;
    }

    public void setRecommended_action(String recommended_action) {
        Recommended_action = recommended_action;
    }

    public String getCapaciotr_id() {
        return Capaciotr_id;
    }

    public void setCapaciotr_id(String capaciotr_id) {
        Capaciotr_id = capaciotr_id;
    }

    public String getEquipment_location() {
        return Equipment_location;
    }

    public void setEquipment_location(String equipment_location) {
        Equipment_location = equipment_location;
    }

    public String getEquipment_number() {
        return Equipment_number;
    }

    public void setEquipment_number(String equipment_number) {
        Equipment_number = equipment_number;
    }

    public String getRated_kv() {
        return Rated_kv;
    }

    public void setRated_kv(String rated_kv) {
        Rated_kv = rated_kv;
    }

    public String getKvar_bank() {
        return Kvar_bank;
    }

    public void setKvar_bank(String kvar_bank) {
        Kvar_bank = kvar_bank;
    }

    public String getNo_of_banks() {
        return No_of_banks;
    }

    public void setNo_of_banks(String no_of_banks) {
        No_of_banks = no_of_banks;
    }

    public String getLoading() {
        return Loading;
    }

    public void setLoading(String loading) {
        Loading = loading;
    }

    public String getEquipment_cable() {
        return Equipment_cable;
    }

    public void setEquipment_cable(String equipment_cable) {
        Equipment_cable = equipment_cable;
    }
}
