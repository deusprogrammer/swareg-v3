package com.swag.registration.domain

import org.springframework.dao.DataIntegrityViolationException

import com.swag.registration.domain.order.Order;

class OrderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [paymentInstanceList: Order.list(params), paymentInstanceTotal: Order.count()]
    }

    def create() {
        [paymentInstance: new Order(params)]
    }

    def save() {
        def paymentInstance = new Order(params)
        if (!paymentInstance.save(flush: true)) {
            render(view: "create", model: [paymentInstance: paymentInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
        redirect(action: "show", id: paymentInstance.id)
    }

    def show(Long id) {
        def paymentInstance = Order.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        [paymentInstance: paymentInstance]
    }

    def edit(Long id) {
        def paymentInstance = Order.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        [paymentInstance: paymentInstance]
    }

    def update(Long id, Long version) {
        def paymentInstance = Order.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (paymentInstance.version > version) {
                paymentInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'payment.label', default: 'Payment')] as Object[],
                          "Another user has updated this Payment while you were editing")
                render(view: "edit", model: [paymentInstance: paymentInstance])
                return
            }
        }

        paymentInstance.properties = params

        if (!paymentInstance.save(flush: true)) {
            render(view: "edit", model: [paymentInstance: paymentInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'payment.label', default: 'Payment'), paymentInstance.id])
        redirect(action: "show", id: paymentInstance.id)
    }

    def delete(Long id) {
        def paymentInstance = Order.get(id)
        if (!paymentInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
            return
        }

        try {
            paymentInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'payment.label', default: 'Payment'), id])
            redirect(action: "show", id: id)
        }
    }
}
