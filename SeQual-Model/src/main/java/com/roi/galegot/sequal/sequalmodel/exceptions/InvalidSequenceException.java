/*
 * This file is part of SeQual.
 *
 * SeQual is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * SeQual is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with SeQual.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.roi.galegot.sequal.sequalmodel.exceptions;

/**
 * The Class InvalidSequenceException.
 */
@SuppressWarnings("serial")
public class InvalidSequenceException extends RuntimeException {

	/**
	 * Instantiates a new invalid sequence exception.
	 */
	public InvalidSequenceException() {
		super("Sequence was malformed or had some sort of error in it.");
	}
}