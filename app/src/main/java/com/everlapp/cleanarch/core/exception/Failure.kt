package com.everlapp.cleanarch.core.exception

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {

    class NetworkConnection: Failure()
    class ServerError: Failure()

    class DatabaseError: Failure()


    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()

}