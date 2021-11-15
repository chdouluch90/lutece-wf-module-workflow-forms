/*
 * Copyright (c) 2002-2021, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.workflow.modules.forms.service.task;

import java.util.List;

import javax.inject.Inject;

import fr.paris.lutece.plugins.forms.business.Question;
import fr.paris.lutece.plugins.forms.business.QuestionHome;
import fr.paris.lutece.plugins.workflow.modules.forms.business.CompleteFormResponseTaskHistory;
import fr.paris.lutece.plugins.workflow.modules.forms.business.ICompleteFormResponseTaskHistoryDAO;
import fr.paris.lutece.plugins.workflowcore.business.resource.ResourceHistory;
import fr.paris.lutece.plugins.workflowcore.service.task.ITask;

public class CompleteFormResponseTaskHistoryService implements ICompleteFormResponseTaskHistoryService
{
    @Inject
    private ICompleteFormResponseTaskHistoryDAO _completeFormResponseTaskHistoryDAO;

    @Override
    public void create( CompleteFormResponseTaskHistory completeFormResponseTaskHistory )
    {
        _completeFormResponseTaskHistoryDAO.insert( completeFormResponseTaskHistory );
    }

    @Override
    public List<CompleteFormResponseTaskHistory> load( int nIdHistory, int nIdTask )
    {
        List<CompleteFormResponseTaskHistory> listCompleteFormResponseTaskHistory = _completeFormResponseTaskHistoryDAO
                .selectEditFormResponseHistoryByIdHistoryAndIdTask( nIdHistory, nIdTask );
        listCompleteFormResponseTaskHistory.forEach( ( CompleteFormResponseTaskHistory history ) -> {
            Question question = QuestionHome.findByPrimaryKey( history.getQuestion( ).getId( ) );
            question.setIterationNumber( history.getQuestion( ).getIterationNumber( ) );
            history.setQuestion( question );
        } );
        return listCompleteFormResponseTaskHistory;
    }

    @Override
    public void removeAllByHistoryAndTask( ResourceHistory history, ITask task )
    {
        _completeFormResponseTaskHistoryDAO.deleteByIdHistoryAndTask( history.getId( ), task.getId( ) );
    }
}