package com.swag.registration.domain

import com.swag.registration.*
import com.swag.registration.security.*
import java.util.UUID

class Event implements Serializable, Payable, Ownable {
	transient emailService
	transient springSecurityService
	
    String uuid = UUID.randomUUID().toString()
    String apiKey = UUID.randomUUID().toString()
    String name
    String homePage = "http://"
    String merchantEmail
    Currency currency = Currency.USD
    Double taxRate = 0.0825
	
	String logoUrl

    String year = (1900 + new Date().getYear()).toString()
    Integer numeral = 1

    static hasMany = [registrations: Registration, levels: RegistrationLevel, roles: Role, positions: StaffPosition]
    static belongsTo = [user: User]

    public String toString() {
        if (!year.equals("0")) {
            return name + " " + year
        }
        else if (numeral != 0) {
            return name + " " + numeral
        }
        else {
            return name
        }
    }

    static constraints = {
        homePage nullable: true
        merchantEmail nullable: true
        currency nullable: true
		logoUrl nullable: true
        uuid nullable: true
        apiKey nullable: true
    }

    static mapping = {
        levels sort: 'price'
    }
	
	public ArrayList<User> getStaffList() {
		positions.collect {	StaffPosition p -> 
			p.user 
		}
	}
	
	public boolean userIsStaff(User u = null) {
		if (!u) {
			u = springSecurityService.currentUser
		}
		return u in this.staffList || userIsOwner(u)
	}
	
	public boolean userHasRead(User u = null) {
		if (!u) {
			u = springSecurityService.currentUser
		}
		return positions.find { StaffPosition p ->
			p.user?.id == u.id &&
			(
				p.unpackPermissions()["read"] ||
				p.unpackPermissions()["admin"]
			)
		} != null || userIsOwner(u)
	}
	
	public boolean userHasWrite(User u = null) {
		if (!u) {
			u = springSecurityService.currentUser
		}
		return positions.find { StaffPosition p ->
			p.user?.id == u.id &&
			(
				p.unpackPermissions()["write"] ||
				p.unpackPermissions()["admin"]
			)
		} != null || userIsOwner(u)
	}
	
	public boolean userHasDelete(User u = null) {
		if (!u) {
			u = springSecurityService.currentUser
		}
		return positions.find { StaffPosition p ->
			p.user?.id == u.id &&
			(
				p.unpackPermissions()["delete"] ||
				p.unpackPermissions()["admin"]
			)
		} != null || userIsOwner(u)
	}
	
	public boolean userHasAdmin(User u = null) {
		if (!u) {
			u = springSecurityService.currentUser
		}
		positions.find { StaffPosition p ->
			p.user?.id == u.id &&
			p.unpackPermissions()["admin"]
		} != null || userIsOwner(u)
	}
	
	public boolean userIsOwner(User u = null) {
		if (!u) {
			u = springSecurityService.currentUser
		}
		return u.id == user.id
	}
	
    @Override
    public Double getPrice() {
        return 0
    }

    @Override
    public Double getTotal() {
        return 0.00
    }

    @Override
    public Double getTax() {
        return 0.00
    }

    @Override
    public Double getTaxRate() {
        return taxRate
    }

    @Override
    public User getPurchaser() {
        return user
    }

    @Override
    public Currency getCurrency() {
        return currency
    }

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public User getOwner() {
        return user
    }

    @Override
    public Boolean isPaid() {
        return true
    }
}
