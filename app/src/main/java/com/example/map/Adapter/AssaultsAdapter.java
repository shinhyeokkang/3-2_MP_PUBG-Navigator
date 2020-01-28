package com.example.map.Adapter;

public class AssaultsAdapter {

    private String weaponName, imageLink, weaponDesc, ammo;
    public int hitDamage, bulletSpeed, impactPower, range;

    public String getWeaponName() {
        return weaponName;
    }

    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getWeaponDesc() {
        return weaponDesc;
    }

    public void setWeaponDesc(String weaponDesc) {
        this.weaponDesc = weaponDesc;
    }
    public String getAmmo() {
        return ammo;
    }

    public void setAmmo(String ammo) {
        this.ammo = ammo;
    }

    public int getHitDamage() {
        return hitDamage;
    }

    public void setHitDamage(int hitDamage) {
        this.hitDamage = hitDamage;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    public int getImpactPower() {
        return impactPower;
    }

    public void setImpactPower(int impactPower) {
        this.impactPower = impactPower;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public AssaultsAdapter(String weaponName, String imageLink, String weaponDesc, String ammo, int bulletSpeed, int impactPower, int range, int hitDamage){
        this.weaponName = weaponName;
        this.imageLink = imageLink;
        this.weaponDesc = weaponDesc;
        this.ammo = ammo;
        this.hitDamage = hitDamage;
        this.bulletSpeed = bulletSpeed;
        this.impactPower = impactPower;
        this.range = range;

    }



}
