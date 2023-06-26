const { workerData, parentPort } = require('worker_threads');

const { threadId, numberOfIterations } = workerData;
performComputation(threadId, numberOfIterations);

parentPort.postMessage('Done');

function performComputation(threadId, numberOfIterations) {
    let result = 0;
    for (let i = 0; i < numberOfIterations; i++) {
        // Perform some heavy computation
        result += i * i;
    }
    console.log(`Thread ${threadId} Result: ${result}`);
}
