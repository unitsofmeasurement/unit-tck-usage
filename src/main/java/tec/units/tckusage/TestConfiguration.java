/*
 *  Unit-API - Units of Measurement TCK for Java
 *  Copyright (c) 2005-2017, Jean-Marie Dautelle, Werner Keil, V2COM.
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
package tec.units.tckusage;

import static tec.uom.se.quantity.QuantityDimension.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.measure.Dimension;
import javax.measure.Quantity;
import javax.measure.Unit;
import javax.measure.UnitConverter;
import javax.measure.format.UnitFormat;
import org.reflections.Reflections;

import tec.uom.se.format.SimpleUnitFormat;
import tec.uom.se.function.*;
import tec.uom.se.quantity.NumberQuantity;
import tec.uom.se.quantity.QuantityDimension;
import tec.uom.se.unit.*;
import tec.units.tck.util.ServiceConfiguration;

/**
 * ServiceConfiguration setup class. This is an example TCK setup class,
 * that has to be written by implementors to setup the JSR 363 TCK for running
 * with their implementations.
 * <p>
 * 
 * @author <a href="mailto:units@catmedia.us">Werner Keil</a>
 * @version 0.7, April 3, 2016
 */
public final class TestConfiguration implements ServiceConfiguration {

	@SuppressWarnings("rawtypes")
	public Collection<Class> getQuantityClasses() {
		return Arrays.asList(new Class[] { NumberQuantity.class });
	}

	@SuppressWarnings("rawtypes")
	public Collection<Class> getUnitClasses() {
		return Arrays.asList(new Class[] { BaseUnit.class, AlternateUnit.class,
				ProductUnit.class, TransformedUnit.class });
	}

	public Collection<? extends Unit<?>> getUnits4Test() {
		return Units.getInstance().getUnits();
	}

	public Collection<UnitConverter> getUnitConverters4Test() {
		return Arrays.asList(new UnitConverter[] { new AddConverter(1),
				new ExpConverter(1), new LogConverter(1),
				new MultiplyConverter(0), RationalConverter.of(2, 1), });
	}

	public Collection<UnitFormat> getUnitFormats4Test() {
		return Arrays.asList(new UnitFormat[] { SimpleUnitFormat.getInstance() });
	}

	@SuppressWarnings("rawtypes")
	public Collection<Class> getDimensionClasses() {
		return Arrays.asList(new Class[] { QuantityDimension.class });
	}

	public Collection<Dimension> getBaseDimensions() {
		return Arrays.asList(new Dimension[] { AMOUNT_OF_SUBSTANCE,
				ELECTRIC_CURRENT, LENGTH, LUMINOUS_INTENSITY, MASS,
				TEMPERATURE, TIME });
	}

	@SuppressWarnings("rawtypes")
	public Collection<Class<? extends Quantity>> getSupportedQuantityTypes() {
		Reflections reflections = new Reflections("javax.measure");
		Set<Class<? extends Quantity>> subTypes = reflections
				.getSubTypesOf(Quantity.class);
		return subTypes;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Unit getUnit4Type(Class quantityType) {
		return Units.getInstance().getUnit(quantityType);
	}
}