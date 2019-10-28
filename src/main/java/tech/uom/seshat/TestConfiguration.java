/*
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2016, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363 nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tech.uom.seshat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Set;

import javax.measure.Dimension;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.format.UnitFormat;
import org.reflections.Reflections;

import tec.units.tck.util.ServiceConfiguration;


/**
 * ServiceConfiguration setup class. This is an example TCK setup class,
 * that has to be written by implementors to setup the JSR 363 TCK for running
 * with their implementations.
 * <p>
 *
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.9, July 12, 2019
 */
public final class TestConfiguration implements ServiceConfiguration {
    /**
     * Returns the {@link Quantity} implementations defined by a specific class.
     * The returned list does not include the proxy classes.
     */
    @SuppressWarnings("rawtypes")
    public Collection<Class> getQuantityClasses() {
        final ArrayList<Class> classes = new ArrayList<>();
        for (final Class<?> c : Scalar.class.getDeclaredClasses()) {
            if (Quantity.class.isAssignableFrom(c)) {
                classes.add(c);
            }
        }
        return classes;
    }

    @SuppressWarnings("rawtypes")
    public Collection<Class> getUnitClasses() {
        return Arrays.asList(new Class[] { ConventionalUnit.class, SystemUnit.class });
    }

    public Collection<? extends Unit<?>> getUnits4Test() {
        return UnitServices.current().getSystemOfUnitsService().getSystemOfUnits().getUnits();
    }

    public Collection<UnitConverter> getUnitConverters4Test() {
        return Arrays.asList(new UnitConverter[] { LinearConverter.create(2, 1) });
    }

    public Collection<UnitFormat> getUnitFormats4Test() {
        return Arrays.asList(new UnitFormat[] { new tech.uom.seshat.UnitFormat(Locale.ENGLISH) });
    }

    @SuppressWarnings("rawtypes")
    public Collection<Class> getDimensionClasses() {
        return Arrays.asList(new Class[] { UnitDimension.class });
    }

    public Collection<Dimension> getBaseDimensions() {
        final Unit<?>[] bases = {
            Units.METRE,
            Units.KILOGRAM,
            Units.SECOND,
            Units.AMPERE,
            Units.KELVIN,
            Units.MOLE,
            Units.LUMEN
        };
        final Dimension[] dimensions = new Dimension[bases.length];
        for (int i=0; i<bases.length; i++) {
            dimensions[i] = bases[i].getDimension();
        }
        return Arrays.asList(dimensions);
    }

    @SuppressWarnings("rawtypes")
    public Collection<Class<? extends Quantity>> getSupportedQuantityTypes() {
        Reflections reflections = new Reflections("javax.measure");
        Set<Class<? extends Quantity>> subTypes = reflections
                        .getSubTypesOf(Quantity.class);
        /*
         * Remove quantities for which Seshat does not define any units.
         */
        subTypes.remove(javax.measure.quantity.Acceleration.class);
        subTypes.remove(javax.measure.quantity.CatalyticActivity.class);
        subTypes.remove(javax.measure.quantity.RadiationDoseAbsorbed.class);
        subTypes.remove(javax.measure.quantity.RadiationDoseEffective.class);
        subTypes.remove(javax.measure.quantity.Radioactivity.class);
        return subTypes;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Unit getUnit4Type(Class quantityType) {
        return UnitServices.current().getSystemOfUnitsService().getSystemOfUnits().getUnit(quantityType);
    }
}