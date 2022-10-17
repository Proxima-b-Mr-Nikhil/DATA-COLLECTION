package com.vbgroups.vbtantrav1.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.vbgroups.vbtantrav1.Model.userAssets;
import com.vbgroups.vbtantrav1.R;
import com.vbgroups.vbtantrav1.Surveyor.Assets;
import com.vbgroups.vbtantrav1.Userpanel.ImageActivity;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel;
import com.vbgroups.vbtantrav1.Userpanel.UserPanel1;

public class AssetsAdapter extends FirebaseRecyclerAdapter<userAssets,AssetsAdapter.myviewholder> {

    public AssetsAdapter(FirebaseRecyclerOptions<userAssets> options) {
        super(options);
    }

    Context context;
    @Override
    protected void onBindViewHolder(@NonNull myviewholder myviewholder, int i, @NonNull final userAssets userAssets) {


        myviewholder.assetname.setText(userAssets.getAsset_name());
        myviewholder.uploadedby.setText(userAssets.getUploaderid());
        if (userAssets.getItemimage()!=null) {
            myviewholder.img.setBackgroundResource(0);
            Glide.with(myviewholder.img.getContext()).load(userAssets.getItemimage()).into(myviewholder.img);
            myviewholder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (userAssets.getItemimage()!=null){
                        Intent intent=new Intent(context, ImageActivity.class);
                        intent.putExtra("imgid",userAssets.getItemimage());
                        context.startActivity(intent);
                    }else {
                        Toast.makeText(context, "loading...", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else {
            myviewholder.img.setBackgroundResource(0);;
            Glide.with(myviewholder.img.getContext()).load(R.drawable.ic_do_not_disturb_alt_black_24dp).into(myviewholder.img);
            myviewholder.img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Toast.makeText(context, "No image found", Toast.LENGTH_SHORT).show();
                }
            });
        }


        myviewholder.sourcename.setText(userAssets.getSource_name());
        if (myviewholder.sourcename.getText().toString().isEmpty()){
            myviewholder.lsourcename.setVisibility(View.GONE);
        }else {
            myviewholder.lsourcename.setVisibility(View.VISIBLE);
        }
            myviewholder.ratedvoltage.setText(userAssets.getRated_voltage());
        if (myviewholder.ratedvoltage.getText().toString().isEmpty()){
            myviewholder.lratedvoltage.setVisibility(View.GONE);
        }else {
                myviewholder.lratedvoltage.setVisibility(View.VISIBLE);
        }
        myviewholder.comingfrom.setText(userAssets.getComing_from());
        if (myviewholder.comingfrom.getText().toString().isEmpty()){
            myviewholder.lcomingfrom.setVisibility(View.GONE);
        }else {
            myviewholder.lcomingfrom.setVisibility(View.VISIBLE);
        }
            myviewholder.shortcircuitka.setText(userAssets.getShort_circuit());
        if (myviewholder.shortcircuitka.getText().toString().isEmpty()){
            myviewholder.lshortcircuitka.setVisibility(View.GONE);
        }else {
            myviewholder.lshortcircuitka.setVisibility(View.VISIBLE);
        }

        myviewholder.xrratio.setText(userAssets.getXR_ratio());
        if (myviewholder.xrratio.getText().toString().isEmpty()){
            myviewholder.lxrratio.setVisibility(View.GONE);
        }else {
            myviewholder.lxrratio.setVisibility(View.VISIBLE);
        }
        myviewholder.remarks.setText(userAssets.getRemark());
        if (myviewholder.remarks.getText().toString().isEmpty()){
            myviewholder.lremarks.setVisibility(View.GONE);
        }else {
            myviewholder.lremarks.setVisibility(View.VISIBLE);
        }
        myviewholder.transformername.setText(userAssets.getTransformer_name());
        if (myviewholder.transformername.getText().toString().isEmpty()){
            myviewholder.ltransformername.setVisibility(View.GONE);
        }else {
            myviewholder.ltransformername.setVisibility(View.VISIBLE);
        }
        myviewholder.feedername.setText(userAssets.getFeeder_name());
        if (myviewholder.feedername.getText().toString().isEmpty()){
            myviewholder.lfeedername.setVisibility(View.GONE);
        }else {
            myviewholder.lfeedername.setVisibility(View.VISIBLE);
        }
        myviewholder.make.setText(userAssets.getMake());
        if (myviewholder.make.getText().toString().isEmpty()){
            myviewholder.lmake.setVisibility(View.GONE);
        }else {
            myviewholder.lmake.setVisibility(View.VISIBLE);
        }
        myviewholder.model.setText(userAssets.getModel());
        if (myviewholder.model.getText().toString().isEmpty()){
            myviewholder.lmodel.setVisibility(View.GONE);
        }else {
            myviewholder.lmodel.setVisibility(View.VISIBLE);
        }
        myviewholder.rating.setText(userAssets.getRating());
        if (myviewholder.rating.getText().toString().isEmpty()){
            myviewholder.lrating.setVisibility(View.GONE);
        }else {
            myviewholder.lrating.setVisibility(View.VISIBLE);
        }
        myviewholder.primaryvoltage.setText(userAssets.getPrimary_voltage());
        if (myviewholder.primaryvoltage.getText().toString().isEmpty()){
            myviewholder.primaryvoltage.setVisibility(View.GONE);
            myviewholder.lprimaryvoltage.setVisibility(View.GONE);
        }
        myviewholder.secondaryvoltage1.setText(userAssets.getSecondary_voltage_1());
        if (myviewholder.secondaryvoltage1.getText().toString().isEmpty()){
            myviewholder.lsecondaryvoltage1.setVisibility(View.GONE);
        }else {
            myviewholder.lsecondaryvoltage1.setVisibility(View.VISIBLE);
        }
        myviewholder.secondaryvoltage2.setText(userAssets.getSecondary_voltage_2());
        if (myviewholder.secondaryvoltage2.getText().toString().isEmpty()){
            myviewholder.lsecondaryvoltage2.setVisibility(View.GONE);
        }else {
            myviewholder.lsecondaryvoltage1.setVisibility(View.VISIBLE);
        }
        myviewholder.z.setText(userAssets.getZ());
        if (myviewholder.z.getText().toString().isEmpty()){
            myviewholder.lz.setVisibility(View.GONE);
        }else {
            myviewholder.lz.setVisibility(View.VISIBLE);
        }
        myviewholder.sourcetype.setText(userAssets.getSource_type());
        if (myviewholder.sourcetype.getText().toString().isEmpty()){
            myviewholder.lsourcetype.setVisibility(View.GONE);
        }else {
            myviewholder.lsourcetype.setVisibility(View.VISIBLE);
        }
        myviewholder.generatorname.setText(userAssets.getGenerator_name());
        if (myviewholder.generatorname.getText().toString().isEmpty()){
            myviewholder.lgeneratorname.setVisibility(View.GONE);
        }else {
            myviewholder.lgeneratorname.setVisibility(View.VISIBLE);
        }
        myviewholder.xd.setText(userAssets.getXd());
        if (myviewholder.xd.getText().toString().isEmpty()){
            myviewholder.lxd.setVisibility(View.GONE);
        }else {
            myviewholder.lxd.setVisibility(View.VISIBLE);
        }
        myviewholder.voltage.setText(userAssets.getVoltage());
        if (myviewholder.voltage.getText().toString().isEmpty()){
            myviewholder.lvoltage.setVisibility(View.GONE);
        }else {
            myviewholder.voltage.setVisibility(View.VISIBLE);
        }
        myviewholder.conductortype.setText(userAssets.getConductor_type());
        if (myviewholder.conductortype.getText().toString().isEmpty()){
            myviewholder.lconductortype.setVisibility(View.GONE);
        }else {
            myviewholder.lconductortype.setVisibility(View.VISIBLE);
        }
        myviewholder.configurationtype.setText(userAssets.getConfiguration());
        if (myviewholder.configurationtype.getText().toString().isEmpty()){
            myviewholder.lconfigurationtype.setVisibility(View.GONE);
        }else {
            myviewholder.lconfigurationtype.setVisibility(View.VISIBLE);
        }
        myviewholder.type.setText(userAssets.getType());
        if (myviewholder.type.getText().toString().isEmpty()){
            myviewholder.ltype.setVisibility(View.GONE);
        }else {
            myviewholder.ltype.setVisibility(View.VISIBLE);
        }
        myviewholder.feederno.setText(userAssets.getFeeder_no());
        if (myviewholder.feederno.getText().toString().isEmpty()){
            myviewholder.lfeederno.setVisibility(View.GONE);
        }else {
            myviewholder.lfeederno.setVisibility(View.VISIBLE);
        }

        myviewholder.maxvoltage.setText(userAssets.getMaximum_voltage());
        if (myviewholder.maxvoltage.getText().toString().isEmpty()){
            myviewholder.lmaxvoltage.setVisibility(View.GONE);
        }else {
            myviewholder.lmaxvoltage.setVisibility(View.VISIBLE);
        }
        myviewholder.framerating.setText(userAssets.getFrame_rating());
        if (myviewholder.framerating.getText().toString().isEmpty()){
            myviewholder.lframerating.setVisibility(View.GONE);
        }else {
            myviewholder.lframerating.setVisibility(View.VISIBLE);
        }
        myviewholder.trip.setText(userAssets.getTrip());
        if (myviewholder.trip.getText().toString().isEmpty()){
            myviewholder.ltrip.setVisibility(View.GONE);
        }else {
            myviewholder.ltrip.setVisibility(View.VISIBLE);
        }
        myviewholder.plug.setText(userAssets.getPlug());
        if (myviewholder.plug.getText().toString().isEmpty()){
            myviewholder.lplug.setVisibility(View.GONE);
        }else {
            myviewholder.lplug.setVisibility(View.VISIBLE);
        }
        myviewholder.ltpu.setText(userAssets.getLtpu());
        if (myviewholder.ltpu.getText().toString().isEmpty()){
            myviewholder.lltpu.setVisibility(View.GONE);
        }else {
            myviewholder.lltpu.setVisibility(View.VISIBLE);
        }
        myviewholder.ltd.setText(userAssets.getLtd());
        if (myviewholder.ltd.getText().toString().isEmpty()){
            myviewholder.lltd.setVisibility(View.GONE);
        }
        myviewholder.stpu.setText(userAssets.getStpu());
        if (myviewholder.stpu.getText().toString().isEmpty()){
            myviewholder.lstpu.setVisibility(View.GONE);
        }else {
            myviewholder.lstpu.setVisibility(View.VISIBLE);
        }
        myviewholder.std.setText(userAssets.getStd());
        if (myviewholder.std.getText().toString().isEmpty()){
            myviewholder.lstd.setVisibility(View.GONE);
        }else {
            myviewholder.lstd.setVisibility(View.VISIBLE);
        }
        myviewholder.instantaneous.setText(userAssets.getInstantaneous());
        if (myviewholder.instantaneous.getText().toString().isEmpty()){
            myviewholder.linstantaneous.setVisibility(View.GONE);
        }else {
            myviewholder.linstantaneous.setVisibility(View.VISIBLE);
        }
        myviewholder.framerating.setText(userAssets.getFrame_rating());
        if (myviewholder.framerating.getText().toString().isEmpty()){
            myviewholder.lframerating.setVisibility(View.GONE);
        }else {
            myviewholder.lframerating.setVisibility(View.VISIBLE);
        }
        myviewholder.primaryct.setText(userAssets.getPrimary_ct());
        if (myviewholder.primaryct.getText().toString().isEmpty()){
            myviewholder.lprimaryct.setVisibility(View.GONE);
        }else {
            myviewholder.lprimaryct.setVisibility(View.VISIBLE);
        }
        myviewholder.secondaryct.setText(userAssets.getSecondary_ct());
        if (myviewholder.secondaryct.getText().toString().isEmpty()){
            myviewholder.lsecondaryct.setVisibility(View.GONE);
        }else {
            myviewholder.lsecondaryct.setVisibility(View.VISIBLE);
        }
        myviewholder.l1.setText(userAssets.getL1());
        if (myviewholder.l1.getText().toString().isEmpty()){
            myviewholder.ll1.setVisibility(View.GONE);
        }else {
            myviewholder.ll1.setVisibility(View.VISIBLE);
        }
        myviewholder.t1.setText(userAssets.getT1());
        if (myviewholder.t1.getText().toString().isEmpty()){
            myviewholder.lt1.setVisibility(View.GONE);
        }
        else {
            myviewholder.lt1.setVisibility(View.VISIBLE);
        }
        myviewholder.l2.setText(userAssets.getL2());
        if (myviewholder.l2.getText().toString().isEmpty()){
            myviewholder.ll2.setVisibility(View.GONE);
        }
        myviewholder.t2.setText(userAssets.getT2());
        if (myviewholder.t2.getText().toString().isEmpty()){
            myviewholder.lt2.setVisibility(View.GONE);
        }else {
            myviewholder.lt2.setVisibility(View.VISIBLE);
        }
        myviewholder.l3.setText(userAssets.getL3());
        if (myviewholder.l3.getText().toString().isEmpty()){
            myviewholder.ll3.setVisibility(View.GONE);
        }else {
            myviewholder.ll3.setVisibility(View.VISIBLE);
        }
        myviewholder.connectedfrom.setText(userAssets.getConnected_from());
        if (myviewholder.connectedfrom.getText().toString().isEmpty()){
            myviewholder.lconnectedfrom.setVisibility(View.GONE);
        }else {
            myviewholder.lconnectedfrom.setVisibility(View.VISIBLE);
        }
        myviewholder.connectedto.setText(userAssets.getConnected_to());
        if (myviewholder.connectedto.getText().toString().isEmpty()){
            myviewholder.lconnectedto.setVisibility(View.GONE);
        }
        myviewholder.cablesize.setText(userAssets.getCable_size());
        if (myviewholder.cablesize.getText().toString().isEmpty()){
            myviewholder.lcablesize.setVisibility(View.GONE);
        }else {
            myviewholder.lcablesize.setVisibility(View.VISIBLE);
        }
        myviewholder.noofruns.setText(userAssets.getNo_of_runs());
        if (myviewholder.noofruns.getText().toString().isEmpty()){
            myviewholder.lnoofruns.setVisibility(View.GONE);
        }
        myviewholder.cablelength.setText(userAssets.getCable_length());
        if (myviewholder.cablelength.getText().toString().isEmpty()){
            myviewholder.lcablelength.setVisibility(View.GONE);
        }else {
            myviewholder.lcablelength.setVisibility(View.VISIBLE);
        }
        myviewholder.cablecore.setText(userAssets.getCable_core());
        if (myviewholder.cablecore.getText().toString().isEmpty()){
            myviewholder.lcablecore.setVisibility(View.GONE);
        }else {
            myviewholder.lcablecore.setVisibility(View.VISIBLE);
        }
        myviewholder.cablematerial.setText(userAssets.getCable_material());
        if (myviewholder.cablematerial.getText().toString().isEmpty()){
            myviewholder.lcablematerial.setVisibility(View.GONE);
        }else {
            myviewholder.lcablematerial.setVisibility(View.VISIBLE);
        }
        myviewholder.insulationtype.setText(userAssets.getInsulation_type());
        if (myviewholder.insulationtype.getText().toString().isEmpty()){
            myviewholder.linsulationtype.setVisibility(View.GONE);
        }
        myviewholder.kwrating.setText(userAssets.getKW_rating());
        if (myviewholder.kwrating.getText().toString().isEmpty()){
            myviewholder.lkwrating.setVisibility(View.GONE);
        }else {
            myviewholder.lkwrating.setVisibility(View.VISIBLE);
        }
        myviewholder.loadtype.setText(userAssets.getLoad_type());
        if (myviewholder.loadtype.getText().toString().isEmpty()){
            myviewholder.lloadtype.setVisibility(View.GONE);
        }else {
            myviewholder.lloadtype.setVisibility(View.VISIBLE);
        }
        myviewholder.flarating.setText(userAssets.getFLA_rating());
        if (myviewholder.flarating.getText().toString().isEmpty()){
            myviewholder.lflarating.setVisibility(View.GONE);
        }else {
            myviewholder.lflarating.setVisibility(View.VISIBLE);
        }
        myviewholder.motortype.setText(userAssets.getMotor_type());
        if (myviewholder.motortype.getText().toString().isEmpty()){
            myviewholder.lmotortype.setVisibility(View.GONE);
        }else {
            myviewholder.lmotortype.setVisibility(View.VISIBLE);
        }
        myviewholder.earthpitno.setText(userAssets.getEarthpit_number());
        if (myviewholder.earthpitno.getText().toString().isEmpty()){
            myviewholder.learthpitno.setVisibility(View.GONE);
        }else {
            myviewholder.learthpitno.setVisibility(View.VISIBLE);
        }
        myviewholder.earthpitlocation.setText(userAssets.getEquipment_location());
        if (myviewholder.earthpitlocation.getText().toString().isEmpty()){
            myviewholder.learthpitlocation.setVisibility(View.GONE);
        }else {
            myviewholder.learthpitlocation.setVisibility(View.VISIBLE);
        }
        myviewholder.earthpitconnectedto.setText(userAssets.getEarthpit_connectedto());
        if (myviewholder.earthpitconnectedto.getText().toString().isEmpty()){
            myviewholder.learthpitconnectedto.setVisibility(View.GONE);
        }else {
            myviewholder.learthpitconnectedto.setVisibility(View.VISIBLE);
        }
        myviewholder.pittogroundresistance.setText(userAssets.getPit_to_grid_resistance());
        if (myviewholder.pittogroundresistance.getText().toString().isEmpty()){
            myviewholder.lpittogroundresistance.setVisibility(View.GONE);
        }else {
            myviewholder.lpittogroundresistance.setVisibility(View.VISIBLE);
        }
        myviewholder.pittogridresistance.setText(userAssets.getPit_to_grid_resistance());
        if (myviewholder.pittogridresistance.getText().toString().isEmpty()){
            myviewholder.lpittogridresistance.setVisibility(View.GONE);
        }else {
            myviewholder.lpittogridresistance.setVisibility(View.VISIBLE);
        }
        myviewholder.earthpitmaintenance.setText(userAssets.getEarthpit_maintenance());
        if (myviewholder.earthpitmaintenance.getText().toString().isEmpty()){
            myviewholder.learthpitmaintenance.setVisibility(View.GONE);
        }else {
            myviewholder.learthpitmaintenance.setVisibility(View.VISIBLE);
        }
        myviewholder.physicalstructure.setText(userAssets.getPhysical_structure());
        if (myviewholder.physicalstructure.getText().toString().isEmpty()){
            myviewholder.lphysicalstructure.setVisibility(View.GONE);
        }else {
            myviewholder.lphysicalstructure.setVisibility(View.VISIBLE);
        }
        myviewholder.pitsurroumdings.setText(userAssets.getPit_surroundings());
        if (myviewholder.pitsurroumdings.getText().toString().isEmpty()){
            myviewholder.lpitsurroumdings.setVisibility(View.GONE);
        }else {
            myviewholder.lpitsurroumdings.setVisibility(View.VISIBLE);
        }
        myviewholder.equipmenttype.setText(userAssets.getEquipment_type());
        if (myviewholder.equipmenttype.getText().toString().isEmpty()){
            myviewholder.lequipmenttype.setVisibility(View.GONE);
        }
        myviewholder.equipmentlocation.setText(userAssets.getEquipment_location());
        if (myviewholder.equipmentlocation.getText().toString().isEmpty()){
            myviewholder.lequipmentlocation.setVisibility(View.GONE);
        }else {
            myviewholder.lequipmentlocation.setVisibility(View.VISIBLE);
        }
        myviewholder.equipmentnumber.setText(userAssets.getEquipment_number());
        if (myviewholder.equipmentnumber.getText().toString().isEmpty()){
            myviewholder.lequipmentnumber.setVisibility(View.GONE);
        }else {
            myviewholder.lequipmentnumber.setVisibility(View.VISIBLE);
        }
        myviewholder.equipmentmaintenance.setText(userAssets.getEquipment_maintenance());
        if (myviewholder.equipmentmaintenance.getText().toString().isEmpty()){
            myviewholder.lequipmentmaintenance.setVisibility(View.GONE);
        }else {
            myviewholder.lequipmentmaintenance.setVisibility(View.VISIBLE);
        }
        myviewholder.equipmentsurroundings.setText(userAssets.getEquipment_surroundings());
        if (myviewholder.equipmentsurroundings.getText().toString().isEmpty()){
            myviewholder.lequipmentsurroundings.setVisibility(View.GONE);
        }else {
            myviewholder.lequipmentsurroundings.setVisibility(View.VISIBLE);
        }
        myviewholder.qualityindex.setText(userAssets.getQuality_index());
        if (myviewholder.qualityindex.getText().toString().isEmpty()){
            myviewholder.lqualityindex.setVisibility(View.GONE);
        }else {
            myviewholder.lqualityindex.setVisibility(View.VISIBLE);
        }
        myviewholder.typeofhazard.setText(userAssets.getType_of_hazard());
        if (myviewholder.typeofhazard.getText().toString().isEmpty()){
            myviewholder.ltypeofhazard.setVisibility(View.GONE);
        }else {
            myviewholder.ltypeofhazard.setVisibility(View.VISIBLE);
        }
        myviewholder.recommendedactions.setText(userAssets.getRecommended_action());
        if (myviewholder.recommendedactions.getText().toString().isEmpty()){
            myviewholder.lrecommendedactions.setVisibility(View.GONE);
        }else {
            myviewholder.lrecommendedactions.setVisibility(View.VISIBLE);
        }
        myviewholder.capacitorid.setText(userAssets.getCapaciotr_id());
        if (myviewholder.capacitorid.getText().toString().isEmpty()){
            myviewholder.lcapacitorid.setVisibility(View.GONE);
        }else {
            myviewholder.lcapacitorid.setVisibility(View.VISIBLE);
        }
        myviewholder.ratedkv.setText(userAssets.getRated_kv());
        if (myviewholder.ratedkv.getText().toString().isEmpty()){
            myviewholder.lratedkv.setVisibility(View.GONE);
        }else {
            myviewholder.lratedkv.setVisibility(View.VISIBLE);
        }
        myviewholder.kvarbank.setText(userAssets.getKvar_bank());
        if (myviewholder.kvarbank.getText().toString().isEmpty()){
            myviewholder.lkvarbank.setVisibility(View.GONE);
        }else {
            myviewholder.lkvarbank.setVisibility(View.VISIBLE);
        }
        myviewholder.numberofbanks.setText(userAssets.getNo_of_banks());
        if (myviewholder.numberofbanks.getText().toString().isEmpty()){
            myviewholder.lnumberofbanks.setVisibility(View.GONE);
        }else {
            myviewholder.lnumberofbanks.setVisibility(View.VISIBLE);
        }
        myviewholder.loading.setText(userAssets.getLoading());
        if (myviewholder.loading.getText().toString().isEmpty()){
            myviewholder.lloading.setVisibility(View.GONE);
        }else {
            myviewholder.lloading.setVisibility(View.VISIBLE);
        }
        myviewholder.equipmentcabledata.setText(userAssets.getEquipment_cable());
        if (myviewholder.equipmentcabledata.getText().toString().isEmpty()){
            myviewholder.lequipmentcabledata.setVisibility(View.GONE);
        }else {
            myviewholder.lequipmentcabledata.setVisibility(View.VISIBLE);
        }
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        context = parent.getContext();
        return new myviewholder(view);

    }


    class myviewholder extends RecyclerView.ViewHolder{
        LinearLayout lassetname,lsourcename,lratedvoltage,lcomingfrom,lshortcircuitka,lxrratio,lremarks,luploadedby,lfeedername,ltransformername,lmake,lmodel,lrating,lprimaryvoltage,lsecondaryvoltage1,lsecondaryvoltage2,
        lz,lsourcetype,lgeneratorname,lxd,lvoltage,lconductortype,lconfigurationtype,ltype,lfeederno,lmaxvoltage,lframerating,ltrip,lplug,lltpu,lstpu,lstd,linstantaneous,lprimaryct,lsecondaryct,lltd,ll1,lt1,ll2,lt2,ll3,lconnectedfrom,lconnectedto,lcablesize,lnoofruns,lcablelength,lcablecore,lcablematerial,linsulationtype,
                lkwrating,lloadtype,lflarating,lmotortype,learthpitno,learthpitlocation,learthpitconnectedto,lpittogroundresistance,lpittogridresistance,learthpitmaintenance,lphysicalstructure,lpitsurroumdings,lequipmenttype,lequipmentlocation,lequipmentnumber,lequipmentmaintenance,lequipmentsurroundings,lqualityindex,ltypeofhazard
                ,lrecommendedactions,lcapacitorid,lratedkv,lkvarbank,lnumberofbanks,lloading,lequipmentcabledata;
        TextView assetname,sourcename,ratedvoltage,comingfrom,shortcircuitka,xrratio,remarks,uploadedby,feedername,transformername,make,model,rating,primaryvoltage,secondaryvoltage1,secondaryvoltage2,
        z,sourcetype,generatorname,xd,voltage,conductortype,configurationtype,type,feederno,maxvoltage,framerating,trip,plug,ltpu,stpu,std,instantaneous,primaryct,secondaryct,l1,t1,l2,t2,l3,connectedfrom,connectedto,cablesize,noofruns,cablelength,cablecore,cablematerial,insulationtype,
        kwrating,loadtype,flarating,motortype,earthpitno,earthpitlocation,earthpitconnectedto,pittogroundresistance,pittogridresistance,earthpitmaintenance,physicalstructure,pitsurroumdings,equipmenttype,equipmentlocation,equipmentnumber,equipmentmaintenance,equipmentsurroundings,qualityindex,typeofhazard
                ,recommendedactions,capacitorid,ratedkv,kvarbank,numberofbanks,loading,equipmentcabledata,ltd;
        ImageView img;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img=(ImageView) itemView.findViewById(R.id.aaimg);

            assetname=(TextView)itemView.findViewById(R.id.assetname);
            sourcename=(TextView)itemView.findViewById(R.id.sourcename);
            ratedvoltage=(TextView)itemView.findViewById(R.id.ratedvoltage);
            comingfrom=(TextView)itemView.findViewById(R.id.comingfrom);
            shortcircuitka=(TextView)itemView.findViewById(R.id.shortcircuitka);
            xrratio=(TextView)itemView.findViewById(R.id.xrratio);
            remarks=(TextView)itemView.findViewById(R.id.remarks);
            uploadedby=(TextView)itemView.findViewById(R.id.uploadedby);
            feedername=(TextView)itemView.findViewById(R.id.feedername);
            transformername=(TextView)itemView.findViewById(R.id.transformername);
            make=(TextView)itemView.findViewById(R.id.make);
            model=(TextView)itemView.findViewById(R.id.model);
            rating=(TextView)itemView.findViewById(R.id.rating);
            primaryvoltage=(TextView)itemView.findViewById(R.id.primaryvoltage);
            secondaryvoltage1=(TextView)itemView.findViewById(R.id.secondaryvoltage1);
            secondaryvoltage2=(TextView)itemView.findViewById(R.id.secondaryvoltage2);
            z=(TextView)itemView.findViewById(R.id.z);
            sourcetype=(TextView)itemView.findViewById(R.id.sourcetype);
            generatorname= (TextView)itemView.findViewById(R.id.generatorname);
            xd=(TextView)itemView.findViewById(R.id.xd);
            voltage=(TextView)itemView.findViewById(R.id.voltage);
            conductortype=(TextView)itemView.findViewById(R.id.conductortype);
            configurationtype=(TextView)itemView.findViewById(R.id.configuration);
            type=(TextView)itemView.findViewById(R.id.type);
            feederno=(TextView)itemView.findViewById(R.id.feederno);
            maxvoltage=(TextView)itemView.findViewById(R.id.maximumvoltage);
            framerating=(TextView)itemView.findViewById(R.id.framerating);
            trip=(TextView)itemView.findViewById(R.id.trip);
            plug=(TextView)itemView.findViewById(R.id.plug);
            ltpu=(TextView)itemView.findViewById(R.id.ltpu);
            ltd=(TextView)itemView.findViewById(R.id.ltd);
            stpu=(TextView)itemView.findViewById(R.id.stpu);
            std=(TextView)itemView.findViewById(R.id.std);
            instantaneous=(TextView)itemView.findViewById(R.id.instantaneous);
            primaryct=(TextView)itemView.findViewById(R.id.primaryct);
            secondaryct=(TextView)itemView.findViewById(R.id.secondaryct);
            l1=(TextView)itemView.findViewById(R.id.l1);
            t1=(TextView)itemView.findViewById(R.id.t1);
            l2=(TextView)itemView.findViewById(R.id.l2);
            t2=(TextView)itemView.findViewById(R.id.t2);
            l3=(TextView)itemView.findViewById(R.id.l3);
            connectedfrom=(TextView)itemView.findViewById(R.id.connectedfrom);
            connectedto=(TextView)itemView.findViewById(R.id.connectedto);
            cablesize=(TextView)itemView.findViewById(R.id.cablesize);
            noofruns=(TextView)itemView.findViewById(R.id.noofruns);
            cablelength=(TextView)itemView.findViewById(R.id.cablelength);
            cablecore=(TextView)itemView.findViewById(R.id.cablecore);
            cablematerial=(TextView)itemView.findViewById(R.id.cablematerial);
            insulationtype=(TextView)itemView.findViewById(R.id.insulationtype);
            kwrating=(TextView)itemView.findViewById(R.id.kwrating);
            loadtype=(TextView)itemView.findViewById(R.id.loadtype);
            flarating=(TextView)itemView.findViewById(R.id.flarating);
            motortype=(TextView)itemView.findViewById(R.id.motortype);
            earthpitno=(TextView)itemView.findViewById(R.id.earthpitno);
            earthpitlocation=(TextView)itemView.findViewById(R.id.earthpitlocation);
            earthpitconnectedto=(TextView)itemView.findViewById(R.id.earthpitconnectedto);
            pittogroundresistance=(TextView)itemView.findViewById(R.id.pittogroundresistance);
            pittogridresistance=(TextView)itemView.findViewById(R.id.pittogridresistance);
            earthpitmaintenance=(TextView)itemView.findViewById(R.id.earthpitmaintenance);
            physicalstructure=(TextView)itemView.findViewById(R.id.physicalstructure);
            pitsurroumdings=(TextView)itemView.findViewById(R.id.pitsurroundings);
            equipmenttype=(TextView)itemView.findViewById(R.id.equipmenttype);
            equipmentlocation=(TextView)itemView.findViewById(R.id.equipmentlocation);
            equipmentnumber=(TextView)itemView.findViewById(R.id.equipmentnumber);
            equipmentmaintenance=(TextView)itemView.findViewById(R.id.equipmentmaintenance);
            equipmentsurroundings=(TextView)itemView.findViewById(R.id.equipmentsurroundings);
            qualityindex=(TextView)itemView.findViewById(R.id.qualityindex);
            typeofhazard=(TextView)itemView.findViewById(R.id.typeofhazard);
            recommendedactions=(TextView)itemView.findViewById(R.id.recommendedaction);
            capacitorid=(TextView)itemView.findViewById(R.id.capacitorid);
            ratedkv=(TextView)itemView.findViewById(R.id.ratedkv);
            kvarbank=(TextView)itemView.findViewById(R.id.kvarbank);
            numberofbanks=(TextView)itemView.findViewById(R.id.numberofbanks);
            loading=(TextView)itemView.findViewById(R.id.loading);
            equipmentcabledata=(TextView)itemView.findViewById(R.id.equipmentcabledata);


            lassetname=(LinearLayout) itemView.findViewById(R.id.lassetname);
            lsourcename=(LinearLayout)itemView.findViewById(R.id.lsourcename);
            lratedvoltage=(LinearLayout)itemView.findViewById(R.id.lratedvoltage);
            lcomingfrom=(LinearLayout)itemView.findViewById(R.id.lcomingfrom);
            lshortcircuitka=(LinearLayout)itemView.findViewById(R.id.lshortcircuitka);
            lxrratio=(LinearLayout)itemView.findViewById(R.id.lxrratio);
            lremarks=(LinearLayout)itemView.findViewById(R.id.lremarks);
            luploadedby=(LinearLayout)itemView.findViewById(R.id.luploadedby);
            lfeedername=(LinearLayout) itemView.findViewById(R.id.lfeedername);
            ltransformername=(LinearLayout)itemView.findViewById(R.id.ltransformername);
            lmake=(LinearLayout)itemView.findViewById(R.id.lmake);
            lmodel=(LinearLayout)itemView.findViewById(R.id.lmodel);
            lrating=(LinearLayout)itemView.findViewById(R.id.lrating);
            lprimaryvoltage=(LinearLayout)itemView.findViewById(R.id.lprimaryvoltage);
            lsecondaryvoltage1=(LinearLayout)itemView.findViewById(R.id.lsecondaryvoltage1);
            lsecondaryvoltage2=(LinearLayout)itemView.findViewById(R.id.lsecondaryvoltage2);
            lz=(LinearLayout)itemView.findViewById(R.id.lz);
            lsourcetype=(LinearLayout)itemView.findViewById(R.id.lsourcetype);

            lgeneratorname= (LinearLayout) itemView.findViewById(R.id.lgeneratorname);
            lxd=(LinearLayout)itemView.findViewById(R.id.lxd);
            lvoltage=(LinearLayout)itemView.findViewById(R.id.lvoltage);
            lconductortype=(LinearLayout)itemView.findViewById(R.id.lconductortype);
            lconfigurationtype=(LinearLayout)itemView.findViewById(R.id.lconfiguration);
            ltype=(LinearLayout)itemView.findViewById(R.id.ltype);
            lfeederno=(LinearLayout)itemView.findViewById(R.id.lfeederno);
            lmaxvoltage=(LinearLayout)itemView.findViewById(R.id.lmaximumvoltage);
            lframerating=(LinearLayout)itemView.findViewById(R.id.lframerating);
            ltrip=(LinearLayout)itemView.findViewById(R.id.ltrip);
            lplug=(LinearLayout)itemView.findViewById(R.id.lplug);
            lltpu=(LinearLayout)itemView.findViewById(R.id.lltpu);
            lstpu=(LinearLayout)itemView.findViewById(R.id.lstpu);
            lstd=(LinearLayout)itemView.findViewById(R.id.lstd);
            linstantaneous=(LinearLayout)itemView.findViewById(R.id.linstantaneous);
            lprimaryct=(LinearLayout)itemView.findViewById(R.id.lprimaryct);
            lsecondaryct=(LinearLayout)itemView.findViewById(R.id.lsecondaryct);
            ll1=(LinearLayout)itemView.findViewById(R.id.ll1);
            lt1=(LinearLayout)itemView.findViewById(R.id.lt1);
            ll2=(LinearLayout)itemView.findViewById(R.id.ll2);
            lt2=(LinearLayout)itemView.findViewById(R.id.lt2);
            ll3=(LinearLayout)itemView.findViewById(R.id.ll3);
            lconnectedfrom=(LinearLayout)itemView.findViewById(R.id.lconnectedfrom);
            lconnectedto=(LinearLayout)itemView.findViewById(R.id.lconnectedto);
            lcablesize=(LinearLayout)itemView.findViewById(R.id.lcablesize);
            lnoofruns=(LinearLayout)itemView.findViewById(R.id.lnoofruns);
            lcablelength=(LinearLayout)itemView.findViewById(R.id.lcablelength);
            lcablecore=(LinearLayout)itemView.findViewById(R.id.lcablecore);
            lcablematerial=(LinearLayout)itemView.findViewById(R.id.lcablematerial);
            linsulationtype=(LinearLayout)itemView.findViewById(R.id.linsulationtype);
            lkwrating=(LinearLayout)itemView.findViewById(R.id.lkwrating);
            lloadtype=(LinearLayout)itemView.findViewById(R.id.lloadtype);
            lflarating=(LinearLayout)itemView.findViewById(R.id.lflarating);
            lmotortype=(LinearLayout)itemView.findViewById(R.id.lmotortype);
            learthpitno=(LinearLayout)itemView.findViewById(R.id.learthpitno);
            learthpitlocation=(LinearLayout)itemView.findViewById(R.id.learthpitlocation);
            learthpitconnectedto=(LinearLayout)itemView.findViewById(R.id.learthpitconnectedto);
            lpittogroundresistance=(LinearLayout)itemView.findViewById(R.id.lpittogroundresistance);
            lpittogridresistance=(LinearLayout)itemView.findViewById(R.id.lpittogridresistance);
            learthpitmaintenance=(LinearLayout)itemView.findViewById(R.id.learthpitmaintenance);
            lphysicalstructure=(LinearLayout)itemView.findViewById(R.id.lphysicalstructure);
            lpitsurroumdings=(LinearLayout)itemView.findViewById(R.id.lpitsurroundings);
            lequipmenttype=(LinearLayout)itemView.findViewById(R.id.lequipmenttype);
            lequipmentlocation=(LinearLayout)itemView.findViewById(R.id.lequipmentloacation);
            lequipmentnumber=(LinearLayout)itemView.findViewById(R.id.lequipmentnumber);
            lequipmentmaintenance=(LinearLayout)itemView.findViewById(R.id.lequipmentmaintenance);
            lequipmentsurroundings=(LinearLayout)itemView.findViewById(R.id.lequipmentsurroundings);
            lqualityindex=(LinearLayout)itemView.findViewById(R.id.lqualityindex);
            ltypeofhazard=(LinearLayout)itemView.findViewById(R.id.ltypeofhazard);
            lrecommendedactions=(LinearLayout)itemView.findViewById(R.id.lrecommendedaction);
            lcapacitorid=(LinearLayout)itemView.findViewById(R.id.lcapacitorid);
            lratedkv=(LinearLayout)itemView.findViewById(R.id.lratedkv);
            lkvarbank=(LinearLayout)itemView.findViewById(R.id.lkvarbank);
            lnumberofbanks=(LinearLayout)itemView.findViewById(R.id.lnumberofbanks);
            lloading=(LinearLayout)itemView.findViewById(R.id.lloading);
            lequipmentcabledata=(LinearLayout)itemView.findViewById(R.id.lequipmentcabledata);
            lltd=(LinearLayout) itemView.findViewById(R.id.lltd);


        }
    }
}
