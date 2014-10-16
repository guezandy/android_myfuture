/**
 * Copyright 2014 Facebook, Inc.
 *
 * You are hereby granted a non-exclusive, worldwide, royalty-free license to
 * use, copy, modify, and distribute this software in source code or binary
 * form for use in connection with the web services and APIs provided by
 * Facebook.
 *
 * As with any software that integrates with the Facebook platform, your use
 * of this software is subject to the Facebook Developer Principles and
 * Policies [http://developers.facebook.com/policy/]. This copyright notice
 * shall be included in all copies or substantial portions of the software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL
 * THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

package com.myfuture.util;

import java.util.Comparator;

import com.myfuture.model.Talk;

/**
 * A Comparator that sorts talks first by time, then by room.
 */
public class TalkComparator implements Comparator<Talk> {
	/*
	 * This is a Singleton, because it has no state, so it's silly to make new
	 * ones.
	 */
	final private static TalkComparator instance = new TalkComparator();

	public static TalkComparator get() {
		return instance;
	}

	@Override
	public int compare(Talk lhs, Talk rhs) {
		int startCompare = lhs.getSlot().getStartTime()
				.compareTo(rhs.getSlot().getStartTime());
		if (startCompare != 0) {
			return startCompare;
		}
		return lhs.getRoom().getName().compareTo(rhs.getRoom().getName());
	}
}
