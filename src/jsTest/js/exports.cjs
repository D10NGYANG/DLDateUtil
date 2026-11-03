const assert = require('node:assert/strict');
const path = require('node:path');

// Pass the assembled npm package directory to exercise the public JS boundary.
const dates = require(path.resolve(process.argv[2])).com.d10ng.datelib;
const timestamp = dates.timestampUTCByYMD(2024, 2, 10);
assert.equal(timestamp, 1707523200000n);
assert.equal(dates.timestampToTimeSeconds(timestamp), 1707523200);
assert.equal(dates.timeSecondsToTimestamp(1707523200), timestamp);
assert.equal(dates.timestampToTimeSeconds(-1n), -1);
assert.equal(typeof dates.nowTimestamp(), 'bigint');
assert.equal(dates.asLong(Number.MAX_SAFE_INTEGER), 9007199254740991n);
assert.equal(dates.localDateTimeFormatISO(dates.timestampToUTCDateTime(timestamp)), '2024-02-10T00:00:00');
assert.equal(dates.isDatetimeString('2024-02-29 12:00:00'), true);
assert.equal(dates.isDatetimeString('2023-02-29 12:00:00'), false);
assert.throws(() => dates.timestampToTimeSeconds(2147483648000n));
console.log('Public JavaScript exports and BigInt timestamps passed');
