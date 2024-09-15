import './styles/main.css';
import { AvatarIcon } from './components/AvatarIcon';
import { InputButton } from './components/InputButton';
import { useEffect } from 'react';



export function App() {
 
  return (
    <div className='container p-0'>

      <div className='flex justify-center p-none m-none  bg-white h-[400px] w-full'>
      <AvatarIcon/>
      
      </div>

      <div className='flex justify-center bg-white h-[519px] w-auto'> 
        <InputButton/>
      </div>

    </div>
    
  );
}

