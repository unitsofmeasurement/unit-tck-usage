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
package tech.units.tckusage;

import static tech.uom.impl.enums.unit.SimpleDimension.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.measure.BinaryPrefix;
import javax.measure.Dimension;
import javax.measure.MetricPrefix;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.format.QuantityFormat;
import javax.measure.format.UnitFormat;

import org.reflections.Reflections;

import tech.uom.impl.enums.format.SimpleQuantityFormat;
import tech.uom.impl.enums.format.SimpleUnitFormat;
import tech.uom.impl.enums.function.*;
import tech.uom.impl.enums.quantity.*;
import tech.uom.impl.enums.unit.*;
import tech.units.tck.util.ServiceConfiguration;

/**
 * ServiceLoaderConfiguration setup class. This is an example TCK setup class,
 * that has to be written by implementors to setup the JSR 363 TCK for running
 * with their implementations.
 * <p>
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 */
public final class TestConfiguration implements ServiceConfiguration {

    public Collection<Class> getQuantityClasses() {
	return Arrays.asList(new Class[] { TemperatureQuantity.class,
		TimeQuantity.class });
    }

    public Collection<Class> getUnitClasses() {
	return Arrays.asList(new Class[] { DimensionlessUnit.class,
		DistanceUnit.class, TemperatureUnit.class, TimeUnit.class });
    }

    public Collection<? extends Unit<?>> getUnits4Test() {
	final Set<Unit<?>> units = new HashSet<Unit<?>>();
	units.add(DistanceUnit.METRE);
	units.add(TemperatureUnit.KELVIN);
	units.add(TimeUnit.SECOND);
	return Collections.unmodifiableSet(units);
    }

    public Collection<UnitConverter> getUnitConverters4Test() {
	return Arrays
		.asList(new UnitConverter[] { AbstractConverter.IDENTITY });
    }

    public Collection<UnitFormat> getUnitFormats4Test() {
	return Arrays
		.asList(new UnitFormat[] { SimpleUnitFormat.getInstance() });
    }

    public Collection<Dimension> getBaseDimensions() {
	return Arrays.asList(new Dimension[] { INSTANCE });
    }

    @SuppressWarnings("rawtypes")
    public Collection<Class> getDimensionClasses() {
	return Arrays.asList(new Class[] { SimpleDimension.class });
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Collection<Class> getPrefixClasses() {
        return Arrays.asList(new Class[] { BinaryPrefix.class, MetricPrefix.class });
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Unit getUnit4Type(Class quantityType) {
	// return Units.getInstance().getUnit(quantityType);
	return null;
    }

    @Override
    public Collection<QuantityFormat> getQuantityFormats4Test() {
        return Arrays.asList(new QuantityFormat[] { SimpleQuantityFormat.getInstance() });
    }

	@SuppressWarnings("rawtypes")
	public Collection<Class<? extends Quantity>> getSupportedQuantityTypes() {
		Reflections reflections = new Reflections("javax.measure");
		Set<Class<? extends Quantity>> subTypes = reflections
				.getSubTypesOf(Quantity.class);
		return subTypes;
	}
}