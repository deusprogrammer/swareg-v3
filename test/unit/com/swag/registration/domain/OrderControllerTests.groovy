package com.swag.registration.domain



import org.junit.*

import com.swag.registration.domain.order.Order;

import grails.test.mixin.*

@TestFor(OrderController)
@Mock(Order)
class OrderControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/payment/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.paymentInstanceList.size() == 0
        assert model.paymentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.paymentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.paymentInstance != null
        assert view == '/payment/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/payment/show/1'
        assert controller.flash.message != null
        assert Order.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/payment/list'

        populateValidParams(params)
        def payment = new Order(params)

        assert payment.save() != null

        params.id = payment.id

        def model = controller.show()

        assert model.paymentInstance == payment
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/payment/list'

        populateValidParams(params)
        def payment = new Order(params)

        assert payment.save() != null

        params.id = payment.id

        def model = controller.edit()

        assert model.paymentInstance == payment
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/payment/list'

        response.reset()

        populateValidParams(params)
        def payment = new Order(params)

        assert payment.save() != null

        // test invalid parameters in update
        params.id = payment.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/payment/edit"
        assert model.paymentInstance != null

        payment.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/payment/show/$payment.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        payment.clearErrors()

        populateValidParams(params)
        params.id = payment.id
        params.version = -1
        controller.update()

        assert view == "/payment/edit"
        assert model.paymentInstance != null
        assert model.paymentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/payment/list'

        response.reset()

        populateValidParams(params)
        def payment = new Order(params)

        assert payment.save() != null
        assert Order.count() == 1

        params.id = payment.id

        controller.delete()

        assert Order.count() == 0
        assert Order.get(payment.id) == null
        assert response.redirectedUrl == '/payment/list'
    }
}